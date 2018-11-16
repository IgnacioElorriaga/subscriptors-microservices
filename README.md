# Adidas Spring Boot Challenge
It was released a set of microservices which one interacts with the others and all of them are connected with the eureka server (which it was also developed).

## Framework / Tools used
There are several frameworks developed under Java 8 (using Streams) that I needed to use in order to achieve the requirements.

  - **Maven**, which is a software project management and comprehension tool. And it is basically used for the management of the dependencies of the project (I could have used gradle as an alternative)
  - **Spring boot**, it was mandatory for developing each microservice. And inside the Spring several ones (APIs / Libs / FW / ...) are required to perform different actions, such as:
  -- Eureka, to registry and discover each one among the others (server and clients)
  -- Open Feign (or simply Feign), in order to make HTTP executions between two microservices
    -- Spring Boot Web, because I am releasing web applications.
  -- HATEOAS, which gives us some APIs to create easily REST representations that follow the HATEOAS principle (Hypertext as the Engine of Application State).
  - **JPA**, Java Persistence API. As I am creating a H2 database, in memory database, I would need that framework to access to it.
  - **Swagger**, used to generate the online documentation to make easier to the final consumers to create their own objects to access to me.
  - **Lombok**, it a code generator framework. Using it, I am reducing the amount of code that I have to develop (mainly used for DTO to generate the get's / set's /toString / constructors).
  - **Jackson**, used for the serialization / deserialization of the objects.
  - **Mockito**, used for the Testing purpouses to fake the Objects outside the scope of whether the Unit Tests or Integrated Tests.
  - **JUnit4**, mainly is for doing the Testing part.
  - 

# Content of the project
As I said, there are several Microservices developed. Now it is time to review them:
  - **eureka-server**, it contains the Eureka Server, as it is labeled, to discover all of them
  - **adidas-database-service**, that one was used to be the H2 database and store the information received by adidas-subscription-service. First to store the information checks if that user is already present (through their email, which in Internet is unique for that reason I have considered it will also here).
  - **adidas-email-service**, used as a "black box" I developed the interface and performing a simple validation of the format (as it was said, it was not required to develop anything inside it).
  - **adidas-event-service**, used as a "black box" I developed the interface and performing some operation (I guess it could be a MS to execute some JMS logic). Same as before, as it was said, it was not required to develop anything inside it
  - **adidas-subscription-service**, main Microservice where it was used as a Coordinator and interact with the others ones. It contains all the logic and checks for the input information of the consumer
## How To run it.
It could be run with Maven or inside the Docker. Firstly, I want to point out where they will be built (at least the ports' configuration included for each one), because to simplify it I didn't any other profile than the *default* and no especific environment:
  - **eureka-server**, port=8010. Host=localhost
  - **adidas-database-service**, port=8094. Host=localhost
  - **adidas-email-service**, port=8090. Host=localhost
  - **adidas-event-service**, port=8092. Host=localhost
  - **adidas-subscription-service**, port=8096. Host=localhost
### Maven form

To run all of them in each project. First of all, it has to be run the eureka server and then each micro to be discovered.
```sh
$ mvn clean spring-boot:run
```
### Docker form


# subscriptors-microservices
