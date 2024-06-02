Practical example of integration with rabbitmq using Spring Boot.

# Getting Started

### Stack used
* Docker compose
* Maven
* Spring boot 3
* Java 17
* Rabbitmq
* Makefile

### Step by step to execute this project:
* Clone the project to your machine
* If you have the makefile and docker-compose installed on your machine, just run the command
  *make run-app*
* If you don't have the makefile installed, just run the commands below:
  * docker-compose up -d --wait or docker compose up -d
  * mvn spring-boot:run
* When the project is running, access this page here: [Swagger Doc](http://localhost:8080/swagger-ui/index.html)
* Send a request with the requested data and check the application logs, it will show the data consumed from the queue, which in this case was what you provided in the request.

### My Contact
* [Email](devcristiano.rodrigues@gmail.com)
* [Linkedin](www.linkedin.com/in/cristiano-rodrigues-644b82183)