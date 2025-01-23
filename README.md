Banco de Sangue - Backend 🚑💉
Este é o backend de um sistema de Banco de Sangue desenvolvido com Spring 
Boot. A API oferece endpoints para o envio de dados de candidatos a 
doadores e gera relatórios úteis sobre IMC, obesidade, tipo sanguíneo e 
mais.

Funcionalidades 🛠️
POST /api/candidatos/importar: Envia dados de candidatos (nome, idade, 
peso, altura, estado, gênero, tipo sanguíneo).
GET /api/candidatos/quantidade-por-estado: Retorna a quantidade de 
candidatos por estado.
GET /api/candidatos/imc-medio-faixa-etaria: Retorna o IMC médio por faixa 
etária (faixa de 10 em 10 anos).
GET /api/candidatos/percentual-obesos: Retorna o percentual de obesos 
entre homens e mulheres.
GET /api/candidatos/media-idade-tipo-sanguineo: Retorna a média de idade 
para cada tipo sanguíneo.
GET /api/candidatos/quantidade-doadores-por-tipo-sanguineo: Retorna a 
quantidade de possíveis doadores para cada tipo sanguíneo receptor.
Tecnologias 💻
Backend: Spring Boot
Banco de Dados: MySQL 🗄️
Dependências: Spring Data JPA, Spring Web, MySQL Driver
Pré-requisitos ⚙️
Antes de rodar o projeto, você precisa ter as seguintes ferramentas 
instaladas:

Java JDK 17+ ☕
MySQL para o banco de dados 💾
Maven para build e dependências (ou use o wrapper do Spring Boot) 🧰
Instruções de Configuração 📋
1. Clone o Repositório
Clone o repositório do backend para sua máquina:

bash
Copiar código
git clone https://github.com/usuario/repo-backend.git
cd repo-backend
2. Configuração do Banco de Dados MySQL 🔧
Crie um banco de dados no MySQL:
sql
Copiar código
CREATE DATABASE banco_sangue;
Abra o arquivo src/main/resources/application.properties e configure a 
conexão com o banco de dados:
properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/banco_sangue
spring.datasource.username=root
spring.datasource.password=senha_do_banco
3. Executar o Backend com Spring Boot 🚀
Depois de configurar o banco de dados, você pode rodar o backend com o 
Maven ou Spring Boot:

Com o Maven:
bash
Copiar código
./mvnw spring-boot:run
Ou diretamente com o Spring Boot:
bash
Copiar código
./mvnw spring-boot:run
Agora, a API estará disponível em: http://localhost:8080

Endpoints da API 📡
Abaixo estão os principais endpoints que a API fornece para interagir com 
os dados de candidatos.

1. Enviar Candidatos 📤
Método: POST
Endpoint: /api/candidatos/importar
Body (JSON):
json
Copiar código
[
  {
    "nome": "João da Silva",
    "idade": 25,
    "peso": 70.0,
    "altura": 1.75,
    "estado": "SP",
    "genero": "Masculino",
    "tipoSanguineo": "A+"
  },
  {
    "nome": "Maria Oliveira",
    "idade": 32,
    "peso": 65.0,
    "altura": 1.60,
    "estado": "RJ",
    "genero": "Feminino",
    "tipoSanguineo": "O+"
  }
]
2. Quantidade de Candidatos por Estado 🗺️
Método: GET
Endpoint: /api/candidatos/quantidade-por-estado
Resposta (JSON):
json
Copiar código
{
  "SP": 50,
  "RJ": 35,
  "MG": 40
}
3. IMC Médio por Faixa Etária 📊
Método: GET
Endpoint: /api/candidatos/imc-medio-faixa-etaria
Resposta (JSON):
json
Copiar código
{
  "0-10": 18.5,
  "11-20": 22.3,
  "21-30": 24.1,
  "31-40": 25.2,
  "41-50": 26.0
}
4. Percentual de Obesos entre Homens e Mulheres ⚖️
Método: GET
Endpoint: /api/candidatos/percentual-obesos
Resposta (JSON):
json
Copiar código
{
  "homens": 25.5,
  "mulheres": 32.0
}
5. Média de Idade por Tipo Sanguíneo 🧬
Método: GET
Endpoint: /api/candidatos/media-idade-tipo-sanguineo
Resposta (JSON):
json
Copiar código
{
  "A+": 29.5,
  "O+": 27.0,
  "AB+": 32.0,
  "B+": 28.0
}
6. Quantidade de Possíveis Doadores por Tipo Sanguíneo Receptor 💉
Método: GET
Endpoint: /api/candidatos/quantidade-doadores-por-tipo-sanguineo
Resposta (JSON):
json
Copiar código
{
  "A+": 75,
  "O+": 120,
  "AB+": 50
}
Estrutura do Projeto 📂
Backend (Spring Boot)
src/main/java/com/bancosangue:
CandidatoController.java: Controlador responsável pelos endpoints da API.
CandidatoService.java: Lógica de processamento dos dados dos candidatos.
CandidatoRepository.java: Acesso ao banco de dados.
Candidato.java: Modelo de dados do candidato.
Contribuindo 🤝
Se você quiser contribuir com o projeto, siga os seguintes passos:

Faça um Fork deste repositório.
Crie uma branch para a sua feature (git checkout -b minha-feature).
Commit suas mudanças (git commit -am 'Adicionando nova feature').
Push para a branch (git push origin minha-feature).
Abra um Pull Request.
Licença 📜
Este projeto é licenciado sob a MIT License.

Se tiver qualquer dúvida ou encontrar algum problema, fique à vontade 
para abrir uma issue ou perguntar! 😄


