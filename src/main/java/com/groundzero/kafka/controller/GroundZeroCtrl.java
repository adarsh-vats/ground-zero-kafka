package com.groundzero.kafka.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/groundzero")
public class GroundZeroCtrl {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @GetMapping(value = "/messages")
    public void sendMessage(@RequestParam Map<String, String> request){

        //Producer
        kafkaTemplate.send("demo.topic", String.valueOf(request));

    }

    @GetMapping(value = "/log-my-header")
    public ResponseEntity<Map<String, String>> getHeaders(@RequestHeader Map<String, String> headers){
        return new ResponseEntity<Map<String, String>>(headers, HttpStatus.OK);
    }

    @GetMapping(value = "/my-header")
    public ResponseEntity<Map<String, String>> printHeadersOnConsole(HttpServletRequest request){
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return new ResponseEntity<Map<String, String>>(headers, HttpStatus.OK);
    }

    @GetMapping("/ip-address")
    public String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return "IP address: " + ipAddress;
    }

    @GetMapping(value = "/ping")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<String>("Pong!!", HttpStatus.OK);
    }
}
