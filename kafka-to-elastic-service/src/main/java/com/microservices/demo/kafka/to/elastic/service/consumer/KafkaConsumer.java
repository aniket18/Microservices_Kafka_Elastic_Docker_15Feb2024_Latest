package com.microservices.demo.kafka.to.elastic.service.consumer;

import org.apache.avro.specific.SpecificRecordBase;

import java.io.Serializable;
import java.util.List;

public interface KafkaConsumer<T extends SpecificRecordBase> {

    void receive(List<T> messages, List<Integer> keys, List<Integer> partitions,
                 List<Long> offsets);
}
