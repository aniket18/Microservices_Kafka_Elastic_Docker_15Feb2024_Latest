package com.microservices.demo.kafka.stream.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaStreamResponseModel {
    private String word;
    private Long wordCount;
}
