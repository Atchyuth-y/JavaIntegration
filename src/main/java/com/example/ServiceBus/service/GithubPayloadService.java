package com.example.ServiceBus.service;

import com.example.ServiceBus.model.GithubPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubPayloadService {

    // You can use RestTemplate to send HTTP requests if needed
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public GithubPayloadService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public ResponseEntity<String> sendPayloadToServiceBus(GithubPayload payload) {
        try {
            // Perform any necessary processing on the GithubPayload object
            // For example, you can access payload.getAction(), payload.getAfter(), etc.

            // If you have Azure Service Bus integration code, you can send the payload here
            // Replace the following code with your Azure Service Bus logic
            // Example: serviceBusSender.send(payload);

            // Simulate success
            return ResponseEntity.ok("Data Sent To Topic");
        } catch (Exception e) {
            // Handle exceptions or errors here
            // You can log the error and return an error response if needed
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}

