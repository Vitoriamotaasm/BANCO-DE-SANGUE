package com.banco.blood.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.banco.blood.repository.CandidatoRepository;

@Entity
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String dataNasc;  // Agora a data de nascimento é String
    private String sexo;
    private String mae;
    private String pai;
    private String email;
    private String cep;
    private String endereco;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefoneFixo;
    private String celular;
    private double altura;
    private double peso;
    private String tipoSanguineo;

    // Construtor vazio
    public Candidato() {
    }

    // Construtor com parâmetros
    public Candidato(String nome, String estado, String dataNasc, double peso, double altura, String tipoSanguineo, String sexo) {
        this.nome = nome;
        this.estado = estado;
        this.dataNasc = dataNasc;
        this.peso = peso;
        this.altura = altura;
        this.tipoSanguineo = tipoSanguineo;
        this.sexo = sexo;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

   
    
    

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getIdade() {
        LocalDate nascimento = LocalDate.parse(dataNasc, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    // Adicionando o getter para tipo sanguíneo
    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    // Setter para o tipo sanguíneo
    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
   
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    // Método para calcular IMC
    public double calcularIMC() {
        return peso / Math.pow(altura, 2);
    }
}
