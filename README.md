# Spring boot kafka push metrics to OTEL example

Example of how to push Kafka producers and consumers metrics to OTEL in a Spring Boot application.


## Target State

- Automatically generate messages to kafka topics
- Consume messages from the topic that is produced
- Ability to push metrics to OTEL automatically

# Install

## Installation and Setup

### Prerequisites

- Java JDK 17 or later
- Maven 4.0 or later
- Access to a Kafka cluster

### Steps to Install and Run

1. Clone the repository:


1. Create `src/main/resources/application.properties`


```sh
spring.application.name=kafka-client

spring.kafka.consumer.group-id=my-group-id

spring.kafka.bootstrap-servers=bootstarp-server:9092
spring.kafka.properties.security.protocol=SASL_SSL
spring.kafka.properties.sasl.mechanism=PLAIN
spring.kafka.properties.sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username="<username>" password="<password>";



otel.exporter.otlp.endpoint=http://localhost:4317
otel.metrics.exporter=otlp

logging.level.io.micrometer=TRACE
logging.level.io.opentelemetry=TRACE
logging.level.org.springframework.boot.actuate.metrics=TRACE

# Actuator configuration
management.endpoints.web.exposure.include=*
management.endpoints.web.base-path=/actuator
management.endpoint.prometheus.enabled=true

management.metrics.enable.kafka=true

server.port=8081
```

3. Create a build

```
mvn clean package
```

4. Run the jar file

```
java -jar target/kafka_springboot_push_otel-0.0.1-SNAPSHOT.jar 
```

# notice the logs

Received message: test
```

6. Get metrics

```
curl "http://localhost:8081/actuator/prometheus" |  grep kafka
```