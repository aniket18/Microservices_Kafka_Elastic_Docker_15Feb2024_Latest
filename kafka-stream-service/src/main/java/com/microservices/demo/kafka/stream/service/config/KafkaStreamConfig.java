package com.microservices.demo.kafka.stream.service.config;

import com.microservices.demo.config.KafkaConfigData;
import com.microservices.demo.config.KafkaStreamConfigData;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaStreamConfig {

    private final KafkaConfigData kafkaConfigData;

    private final KafkaStreamConfigData kafkaStreamsConfigData;

    public KafkaStreamConfig(KafkaConfigData kafkaConfig,
                             KafkaStreamConfigData kafkaStreamsConfig) {
        this.kafkaConfigData = kafkaConfig;
        this.kafkaStreamsConfigData = kafkaStreamsConfig;
    }


    @Bean
    @Qualifier("streamConfiguration")
    public Properties streamsConfiguration() {
        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaStreamsConfigData.getApplicationID());
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigData.getBootstrapServers());
        streamsConfiguration.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
                kafkaConfigData.getSchemaRegistryUrl());
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.STATE_DIR_CONFIG, kafkaStreamsConfigData.getStateFileLocation());
        return streamsConfiguration;
    }

}
