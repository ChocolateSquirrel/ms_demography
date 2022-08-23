FROM openjdk:8
EXPOSE 8081
COPY target/ms_demography-0.0.1-SNAPSHOT.jar patient.jar
ENTRYPOINT ["java", "-jar", "/patient.jar"]