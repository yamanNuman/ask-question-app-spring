FROM openjdk:17-jdk-alpine
COPY target/askquestion-0.0.1-SNAPSHOT.jar askquestion.jar
ENTRYPOINT ["java","-jar","/askquestion.jar"]
