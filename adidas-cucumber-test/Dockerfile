# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8094

ARG JAR_FILE=target/adidas-cucumber-service-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} database.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/database.jar"]