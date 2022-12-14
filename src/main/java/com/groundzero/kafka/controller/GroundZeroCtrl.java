package com.groundzero.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/groundzero")
public class GroundZeroCtrl {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @GetMapping(value = "/messages")
    public void sendMessage(@RequestParam Map<String, String> request){
        kafkaTemplate.send("demo.topic", String.valueOf(request));
    }

    @GetMapping(value = "/ping")
    public String ping(@RequestParam Map<String, String> request){
        return "Pong!!";
    }
}
