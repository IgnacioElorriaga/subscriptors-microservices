# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8010

ARG JAR_FILE=target/adidas-eureka-server-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} eureka.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka.jar"]
