package com.example.kafka_springboot_push_otel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

	@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        kafkaTemplate.send("my-topic", "test");
    }
}