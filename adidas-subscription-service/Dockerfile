# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8096

ARG JAR_FILE=target/adidas-subscription-service-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} subscription.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/subscription.jar"]