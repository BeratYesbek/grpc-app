FROM openjdk:latest
ARG JAR_FILE=library-service/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]