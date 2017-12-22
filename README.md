# Trabalho Final - Disciplina Programação com Frameworks e Componentes

## Aplicações

### Financas Server

Servidor RMI 
Persistencia ao Banco de Dados (In memory)

### Financas Lib

Classes Modelos
Contrato de Servicos do RMI

### Financas Client

Cliente RMI
Framework front end

## Utilização

### Clone

Faça clone do repositório https://github.com/JulioCesGO/Financas.git utilizando a opção de incluir submódulos. Exemplo:

git clone --recursive https://github.com/JulioCesGO/Financas.git

### Construir o projeto

Para utilizar este projeto não é necessário abrí-lo no Elipse. Siga os seguintes comandos utilizando apenas um terminal.

Construa o projeto com maven:

mvn clean install

### Executar o server 

A partir da pasta raiz do repositório git, onde temos acesso a todos submodulos, execute o servidor:

java -jar FinancasServer\target\server-0.0.1-SNAPSHOT.jar

### Executar o client

A partir da pasta raiz do repositório git, onde temos acesso a todos submodulos, execute o cliente:

mvn org.apache.tomcat.maven:tomcat7-maven-plugin:2.2:run -pl FinancasClient

### Acessar aplicação client

Com o auxilio de um navegador acesse o seguinte link para abrir a aplicação client

http://localhost:8080/