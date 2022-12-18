package com.groundzero.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GroundZeroKafkaConsumer {

    private static final String topicName = "demo.topic";

    @KafkaListener(topics = "demo.topic", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        System.out.println("Tópico: "+ topicName);
        System.out.println("key: "+ payload.key());
        System.out.println("Headers: "+ payload.headers());
        System.out.println("Partion: "+ payload.partition());
        System.out.println("Order: "+ payload.value());

    }
}
