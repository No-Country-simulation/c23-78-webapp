FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/trackmyfix-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_trackmyfix.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "app_trackmyfix.jar"]