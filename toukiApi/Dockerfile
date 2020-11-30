FROM adoptopenjdk/openjdk11:latest

EXPOSE 60010
ADD /target/touki-api-0.0.1-SNAPSHOT.jar touki-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "touki-api-0.0.1-SNAPSHOT.jar"]
