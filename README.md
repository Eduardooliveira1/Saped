# SAPED
Sistema de Arrecadação para Pesquisa e Desenvolvimento -  Ministério de Minas e Energia (MME).

## Setup do ambiente de desenvolvimento

Este projeto utiliza:
* Angular 5.0.0
* Banco de dados Microsoft SQL Server
* JAVA 8
* Nodejs 10.6.0
* OPEN LDAP
* Spring Boot 1.5.13

Premissas
* JDK 8 instalada
* Nodejs instalado

Na pasta **docker** localizado em **/servico/src/main** existe um docker compose que já prepara os containers necessários para a aplicação rodar.
Utilize o comando abaixo para subir os containers

```` Java
    $ docker-compose -f docker-compose.yml up -d
```` 

Ao subir o container do Microsoft SQL Server, conecte no banco de dados com os seguintes dados:
* Server: localhost
* Authentication type: SQL Login
* User name: SA
* Password: yourStrong(!)Password

Após conectar com o banco, execute o comando abaixo para a criação do banco de dados:

```` SQL
    create database db_Saped
    go
```` 

Para subir o cliente, utilizar os comandos dentro do diretório cliente


```` Java
    $ npm install 
```` 


```` Java
    $ npm start
```` 

O backend pode ser iniciado localmente fora da IDE através do comando dentro do diretório servico:

```` Java
    $ ./mvnw
```` 

Em desenvolvimento é necessário executar o backend utiliziando os seguintes profiles: dev, liquibase.

Exemplo para utilização fora da IDE:
```` Java
    $ ./mvnw -P dev,liquibase
```` 

