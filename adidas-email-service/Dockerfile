# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8090

ARG JAR_FILE=target/email-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} email.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/email.jar"]