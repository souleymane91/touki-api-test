FROM adoptopenjdk/openjdk11:latest

EXPOSE 60012

ADD /target/travel-payouts-gateway-0.0.1-SNAPSHOT.jar travel-payouts-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "travel-payouts-gateway-0.0.1-SNAPSHOT.jar"]
