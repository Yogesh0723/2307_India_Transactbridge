package com.example.transactbridge.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    // Kafka Producer Configurations
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerProps));
    }

    // Kafka Consumer Configurations (if needed)
//    @Bean
//    public MessageListenerContainer messageListenerContainer() {
//        // Kafka consumer configuration
//        Map<String, Object> consumerProps = new HashMap<>();
//        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "transactbridge-group");
//        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        // Create a ConsumerFactory
//        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps);
//
//        // Create a MessageListenerContainer
//        ConcurrentMessageListenerContainer<String, String> container =
//                new ConcurrentMessageListenerContainer<>(consumerFactory, new MessageListener<String, String>() {
//                    @Override
//                    public void onMessage(ConsumerRecord<String, String> record) {
//                        // Process the message
//                        System.out.println("Consumed message: " + record.value());
//                    }
//                });
//
//        // Set the concurrency to 3, which means 3 threads will consume messages concurrently
//        container.setConcurrency(3);
//
//        return container;
//    }
}