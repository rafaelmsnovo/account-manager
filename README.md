# ACCOUNT MANAGER

 ---- PROCESSO DE COMPILAÇÃO ---

 1 - Clone no projeto
 2 - Abrir pasta raiz
 3 - Executar: mvn clean install


 ---- PROCESSO DE EXECUÇÃO ---

  1 - Abrir pasta raiz
  2 - Executar: java -jar target/accountmanager-1.0-SNAPSHOT.jar
  3 - Aguarde até o Spring Boot startar
  4 - Acessar: http://localhost:8080


OBS:

Por padrão o projeto está configurado para rodar com o banco em memória (h2)

  ----- EM src/main/resources/application.properties ----

  //Database Settings
  //spring.datasource.url=jdbc:h2:mem:dm;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
  //spring.datasource.platform=h2
  //spring.datasource.username=root
  //spring.datasource.password=
  //spring.datasource.driverClassName=org.h2.Driver
  //spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

Para mudar para o banco Mysql basta trocar as configurações para estas mas com a sua database, seu usuario e senha do seu banco

 //MYSQL
 //spring.datasource.url=jdbc:mysql://localhost:3306/teste
 //spring.datasource.username=root
 //spring.datasource.password=suporte
 //pring.datasource.driverClassName=com.mysql.jdbc.Driver



Chave de segurança criptrografada para realizar o Aporte está em propriedades

  ----- EM src/main/resources/application.properties ----

  transaction.key=x3Uk,S?m


Rafael Moreno da Silva Novo 20/10/2017
tel: (34) 99681-5142
skype: rafaelmsnovo
email: rafaelmorenodasilvanovo@gmail.com
