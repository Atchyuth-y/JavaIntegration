package com.example.ServiceBus.service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.example.ServiceBus.model.GithubPayload;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GithubPayloadService {


    static String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javaqueuepolicy;SharedAccessKey=Q5VJN3BXXXc5ZICuMw6uw+mlXyR7z45w9+ASbHCfMIo=;EntityPath=javaqueue";
    static String queueName = "javaqueue";

    public ResponseEntity<String> sendPayloadToServiceBus(GithubPayload payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonData = objectMapper.valueToTree(payload);
            String commitId = jsonData.get("after").asText();

            String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javaqueuepolicy;SharedAccessKey=Q5VJN3BXXXc5ZICuMw6uw+mlXyR7z45w9+ASbHCfMIo=;EntityPath=javaqueue";
            ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                    .connectionString(connectionString)
                    .sender()
                    .queueName("javaqueue")
                    .buildClient();

            ServiceBusMessage message = new ServiceBusMessage(jsonData.toString());
            message.setContentType("application/json");

            senderClient.sendMessage(message);
            senderClient.close();

            return ResponseEntity.ok("Data Sent To Queue");
        }
        catch (Exception ex) {

            // Log the exception for debugging purposes
            ex.printStackTrace();

            return ResponseEntity.status(500).body("Error: " + ex.getMessage());
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

