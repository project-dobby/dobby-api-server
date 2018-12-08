FROM openjdk:8
VOLUME /tmp
COPY ./target/dobby-api-server-0.0.1-SNAPSHOT.jar dobby-api-server.jar
ENTRYPOINT ["java", "-jar", "dobby-api-server.jar"]