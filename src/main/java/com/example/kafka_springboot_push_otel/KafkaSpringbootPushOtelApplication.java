package com.example.kafka_springboot_push_otel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.OpenTelemetry;

@SpringBootApplication
@EnableScheduling
public class KafkaSpringbootPushOtelApplication {

    private final MeterRegistry meterRegistry;
    private final OpenTelemetry openTelemetry;

	public KafkaSpringbootPushOtelApplication(MeterRegistry meterRegistry, OpenTelemetry openTelemetry) {
        this.meterRegistry = meterRegistry;
        this.openTelemetry = openTelemetry;
    }

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringbootPushOtelApplication.class, args);
	}

	@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
		// Example manual push to OTEL
        meterRegistry.counter("test.counter").increment();
        openTelemetry.getMeter("test-meter").counterBuilder("test.otel.counter").build().add(1);
    }
}
