FROM adoptopenjdk/openjdk11:latest

EXPOSE 60014

ADD /target/obryan-software-gateway-0.0.1-SNAPSHOT.jar obryan-software-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "obryan-software-gateway-0.0.1-SNAPSHOT.jar"]
