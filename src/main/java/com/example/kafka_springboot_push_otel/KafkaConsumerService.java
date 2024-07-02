package com.example.kafka_springboot_push_otel;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;

@Service
public class KafkaConsumerService {

 private final MeterRegistry meterRegistry;

    public KafkaConsumerService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group-id2")
    public void listen(String message) {
        System.out.println("Received message2: " + message);
        meterRegistry.counter("kafka.messages.received").increment();
    }
}