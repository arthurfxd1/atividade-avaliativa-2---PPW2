## Objetivo
O objetivo deste projeto é projetar, desenvolver e documentar uma API RESTful para gerenciar projetos e suas respectivas tarefas.  
A aplicação foi desenvolvida utilizando Spring Boot, Spring Data JPA e MySQL, seguindo os princípios REST.

## Como configurar e executar o projeto localmente

Pré-requisitos:
- Java 17 ou superior
- Maven
- MySQL Server
- Um editor ou IDE como Eclipse ou IntelliJ

Passos para configurar:

Clonar o repositório
git clone https://github.com/seu-usuario/gerenciadorprojetos.git

Entrar na pasta do projeto
cd gerenciadorprojetos

Criar o banco de dados no MySQL
CREATE DATABASE projetosdb;

Configurar o arquivo application.properties:
Abra o arquivo src/main/resources/application.properties e edite com suas credenciais do MySQL:

spring.datasource.url=jdbc:mysql://localhost:3306/projetosdb
spring.datasource.username=root
spring.datasource.password=SENHA_DO_SEU_MYSQL
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


Executar o projeto
mvn spring-boot:run

A aplicação será iniciada em:
http://localhost:8080
