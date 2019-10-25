# Sistema auditado com o Hibernate Envers
Sistema básico escrito em Java utilizando o JSF e auditado com o Hibernate Envers.
O arquivo ```src/resources/META-INF/persistence.xml.example``` contém o modelo base para configuração da conexão com o banco de dados. Este arquivo deve ser duplicado e renomeado para ```persistence.xml```.

Para executar o projeto devem ser executados os seguintes comandos:

```mvn install```

```mvn tomact7:run-war```
