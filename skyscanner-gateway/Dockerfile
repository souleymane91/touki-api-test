FROM adoptopenjdk/openjdk11:latest

EXPOSE 60013

ADD /target/skyscanner-gateway-0.0.1-SNAPSHOT.jar skyscanner-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "skyscanner-gateway-0.0.1-SNAPSHOT.jar"]
