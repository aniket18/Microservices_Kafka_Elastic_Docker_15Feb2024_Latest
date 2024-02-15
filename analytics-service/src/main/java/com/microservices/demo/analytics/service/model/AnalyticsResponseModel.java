package com.microservices.demo.analytics.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsResponseModel {
    private UUID id;
    private String word;
    private long wordCount;
}
