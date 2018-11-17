# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8092

ARG JAR_FILE=target/event-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} event.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/event.jar"]