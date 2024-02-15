package com.microservices.demo.kafka.stream.service.runner;

public interface StreamRunner<K, V> {
    void start();
    default V getValueByKey(K key) {
        return null;
    }
}
