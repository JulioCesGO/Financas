**Exericios da aula de Framwworks**

Conteudo

**Financas Server **

Servidor RMI 
Persistencia ao Banco de Dados (In memory)

**Financas Lib **

Classes Modelos
Contrato de Servicos do RMI

**Financas Client **

Cliente RMI
Framework front end

**primeiro passo**

mvn clean install 

**executar o client:**

mvn org.apache.tomcat.maven:tomcat7-maven-plugin:2.2:run -pl FinancasClient

** executar o server **

java -jar /FinancasServer/target/server-0.0.1-SNAPSHOT.jar

