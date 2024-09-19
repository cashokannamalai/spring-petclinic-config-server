FROM openjdk:17
ADD target/spring-petclinic-config-server-3.2.4.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-server.jar"]
