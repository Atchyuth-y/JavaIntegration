package com.example.ServiceBus.service;

import com.example.ServiceBus.model.GithubPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubPayloadService {


//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public GithubPayloadService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public ResponseEntity<String> sendPayloadToServiceBus(GithubPayload payload) {
        try {
            return ResponseEntity.ok("Data Sent To Topic");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}

