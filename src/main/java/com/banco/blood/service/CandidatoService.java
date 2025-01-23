package com.banco.blood.service;

import com.banco.blood.model.Candidato;
import com.banco.blood.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    // Função para salvar candidatos
    public void salvarCandidatos(List<Candidato> candidatos) {
        candidatoRepository.saveAll(candidatos);
    }

    // Função para gerar as estatísticas
    public String gerarEstatisticas() {
        List<Candidato> candidatos = candidatoRepository.findAll();

        // 1. Quantos candidatos temos em cada estado
        Map<String, Long> candidatosPorEstado = candidatos.stream()
                .collect(Collectors.groupingBy(Candidato::getEstado, Collectors.counting()));

        // 2. IMC médio por faixa de idade
        Map<String, Double> imcPorFaixaDeIdade = new HashMap<>();
        Map<String, Integer> contadorImcPorFaixa = new HashMap<>();
        for (Candidato candidato : candidatos) {
            int faixa = (candidato.getIdade() / 10) * 10;
            String faixaIdade = faixa + " a " + (faixa + 9);

            double imc = candidato.calcularIMC();
            imcPorFaixaDeIdade.put(faixaIdade, imcPorFaixaDeIdade.getOrDefault(faixaIdade, 0.0) + imc);
            contadorImcPorFaixa.put(faixaIdade, contadorImcPorFaixa.getOrDefault(faixaIdade, 0) + 1);
        }
        imcPorFaixaDeIdade.forEach((k, v) -> imcPorFaixaDeIdade.put(k, v / contadorImcPorFaixa.get(k)));

        // 3. Percentual de obesos entre homens e mulheres
        long totalHomens = candidatos.stream().filter(c -> "Masculino".equals(c.getSexo())).count();
        long homensObesos = candidatos.stream()
                .filter(c -> "Masculino".equals(c.getSexo()) && c.calcularIMC() > 30)
                .count();
        double percentualHomensObesos = (double) homensObesos / totalHomens * 100;

        long totalMulheres = candidatos.stream().filter(c -> "Feminino".equals(c.getSexo())).count();
        long mulheresObesas = candidatos.stream()
                .filter(c -> "Feminino".equals(c.getSexo()) && c.calcularIMC() > 30)
                .count();
        double percentualMulheresObesas = (double) mulheresObesas / totalMulheres * 100;

        // 4. Média de idade por tipo sanguíneo
        Map<String, Double> mediaIdadePorTipoSanguineo = candidatos.stream()
                .collect(Collectors.groupingBy(Candidato::getTipoSanguineo,
                        Collectors.averagingInt(Candidato::getIdade)));

        // 5. Quantidade de possíveis doadores para cada tipo sanguíneo receptor
        Map<String, Integer> doadoresPorTipo = new HashMap<>();
        for (Candidato c : candidatos) {
            // Compatibilidade de tipos sanguíneos (simplificada)
            String tipoSanguineoReceptor = c.getTipoSanguineo();
            List<String> tiposSanguineosCompatíveis = getTiposSanguineosCompatíveis(tipoSanguineoReceptor);

            for (String tipoCompatível : tiposSanguineosCompatíveis) {
                doadoresPorTipo.put(tipoCompatível, doadoresPorTipo.getOrDefault(tipoCompatível, 0) + 1);
            }
        }

        // Construir a string com as estatísticas
        StringBuilder stats = new StringBuilder();

        stats.append("Candidatos por Estado: " + candidatosPorEstado.toString() + "\n");
        stats.append("IMC Médio por Faixa Etária: " + imcPorFaixaDeIdade.toString() + "\n");
        stats.append("Percentual de Obesos Homens: " + percentualHomensObesos + "%\n");
        stats.append("Percentual de Obesos Mulheres: " + percentualMulheresObesas + "%\n");
        stats.append("Média de Idade por Tipo Sanguíneo: " + mediaIdadePorTipoSanguineo.toString() + "\n");
        stats.append("Quantidade de Possíveis Doadores: " + doadoresPorTipo.toString() + "\n");

        return stats.toString();
    }

    // Função para retornar os tipos sanguíneos compatíveis (simplificado)
    private List<String> getTiposSanguineosCompatíveis(String tipoSanguineo) {
        // Lista simplificada de compatibilidade
        switch (tipoSanguineo) {
            case "A+":
                return Arrays.asList("A+", "AB+");
            case "A-":
                return Arrays.asList("A+", "A-", "AB+", "AB-");
            case "B+":
                return Arrays.asList("B+", "AB+");
            case "B-":
                return Arrays.asList("B+", "B-", "AB+", "AB-");
            case "O+":
                return Arrays.asList("O+", "A+", "B+", "AB+");
            case "O-":
                return Arrays.asList("A+", "A-", "B+", "B-", "AB+", "AB-");
            case "AB+":
                return Arrays.asList("A+", "B+", "AB+", "O+", "O-", "A-", "B-", "AB-", "O-");
            case "AB-":
                return Arrays.asList("A-", "B-", "AB-", "O-");
            default:
                return Collections.emptyList();
        }
    }

    public List<Candidato> obterCandidatosPorEstado(String estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterCandidatosPorEstado'");
    }
}
