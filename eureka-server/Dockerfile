FROM openjdk:21
LABEL authors="Cristian"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8761
ENTRYPOINT ["java", "--enable-preview" ,"-jar","/app.jar"]