package com.example.ServiceBus.service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
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


    static String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javaqueuepolicy;SharedAccessKey=Q5VJN3BXXXc5ZICuMw6uw+mlXyR7z45w9+ASbHCfMIo=;EntityPath=javaqueue";
    static String queueName = "javaqueue";

    public ResponseEntity<String> sendPayloadToServiceBus(GithubPayload payload) {
        try {
            return ResponseEntity.ok("Data Sent To Topic");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    public static void sendMessage() {
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();
        String request = "Hello Java Queue";
        senderClient.sendMessage(new ServiceBusMessage(request));
        System.out.println("new message " + queueName);
    }
}

