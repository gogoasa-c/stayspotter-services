FROM openjdk:21
LABEL authors="Cristian"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "--enable-preview" ,"-jar","/app.jar"]