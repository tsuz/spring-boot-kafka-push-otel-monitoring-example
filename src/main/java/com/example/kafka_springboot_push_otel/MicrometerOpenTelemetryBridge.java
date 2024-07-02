package com.example.kafka_springboot_push_otel;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;

public class MicrometerOpenTelemetryBridge {
    private final OpenTelemetry openTelemetry;
    private final MeterRegistry meterRegistry;

    public MicrometerOpenTelemetryBridge(OpenTelemetry openTelemetry, MeterRegistry meterRegistry) {
        this.openTelemetry = openTelemetry;
        this.meterRegistry = meterRegistry;
    }

    public void initialize() {
        io.opentelemetry.api.metrics.Meter otelMeter = openTelemetry.getMeter("micrometer-bridge");
        
        meterRegistry.getMeters().forEach(micrometerMeter -> {
            if (micrometerMeter.getId().getType() == Meter.Type.COUNTER) {
                LongCounter counter = otelMeter.counterBuilder(micrometerMeter.getId().getName())
                        .setDescription(micrometerMeter.getId().getDescription())
                        .build();
                
                micrometerMeter.measure().forEach(measurement -> 
                    counter.add((long) measurement.getValue())
                );
            }
            // Add similar blocks for other meter types (GAUGE, TIMER, etc.) as needed
        });
    }
}