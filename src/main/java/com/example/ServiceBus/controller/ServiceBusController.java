package com.example.ServiceBus.controller;

import com.example.ServiceBus.model.GithubPayload;
import com.example.ServiceBus.model.WeatherForecast;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.azure.messaging.servicebus.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInput;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ServiceBusController {

    String str = "Sindhujha";

    private static final String[] Summaries = {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
    };

    @GetMapping
    public List<WeatherForecast> getWeatherForecasts() {
        return Arrays.stream(Summaries)
                .map(summary -> new WeatherForecast(
                        LocalDate.now().plusDays(1),
                        ThreadLocalRandom.current().nextInt(-20, 55),
                        summary))
                .collect(Collectors.toList());
    }

    @PostMapping("/webhook") // http://localhost:8080/api/webhook
    public ResponseEntity<String> print(@RequestBody String requestBody) {
        System.out.println("###### Webhook #####");
        System.out.println("###### Webhook ##### " + requestBody);
        return new ResponseEntity<String >(requestBody, HttpStatus.OK);

    }

    @PostMapping("/SentToServiceBus")
    public ResponseEntity<Map<String, String>> sendToServiceBus(@RequestBody GithubPayload payloadJson) {
        try {
            // Your code for sending data to Azure Service Bus
            String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javatopicpolicy;SharedAccessKey=Kk/4YJT6N0le4iXkGcuh/cwpvwhqIdhef+ASbA8UurI=;EntityPath=japatopic";
            ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                    .connectionString(connectionString)
                    .sender()
                    .topicName("japatopic")
                    .buildClient();



            // Create a ServiceBusMessage and set its content
            ServiceBusMessage message = new ServiceBusMessage(String.valueOf(payloadJson));
            message.setContentType("application/json");



            // Send the message
            senderClient.sendMessage(message);



            // Close the sender client
            senderClient.close();



            // Create a JSON response
            Map<String, String> response = new HashMap<>();
            response.put("message", "Data Sent To Topic");



            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            // Handle the error and return an error response
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred: " + ex.getMessage());



            return ResponseEntity.status(500).body(errorResponse);
        }
    }

//    @PostMapping("/SentToServiceBus")
//    public ResponseEntity<String> sendToServiceBus(@RequestBody GithubPayload payloadJson) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//
//
//            // Deserialize the JSON payload into GithubPayload object
//          //  GithubPayload payload = objectMapper.readValue((DataInput) payloadJson, GithubPayload.class);
//
//
//
//            String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javatopicpolicy;SharedAccessKey=Kk/4YJT6N0le4iXkGcuh/cwpvwhqIdhef+ASbA8UurI=;EntityPath=japatopic";
//            ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
//                    .connectionString(connectionString)
//                    .sender()
//                    .topicName("japatopic")
//                    .buildClient();
//
//
//
//            // You can access payload properties like payload.getAfter() or payload.getAction() here
//
//
//
//            ServiceBusMessage message = new ServiceBusMessage(objectMapper.writeValueAsString(payload));
//            message.setContentType("application/json");
//
//
//
//            senderClient.sendMessage(message);
//            senderClient.close();
//
//
//
//            return ResponseEntity.ok("Data Sent To Topic");
//        } catch (IOException| ServiceBusException ex) {
//            return ResponseEntity.ok(ex.toString());
//        }
//    }


//    @PostMapping("/SentToServiceBus")
//    public ResponseEntity<String> sendToServiceBus(@RequestBody GithubPayload payload) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            // Convert the payload object back to JSON string
//            String payloadJson = objectMapper.writeValueAsString(payload);
//
//
//
//            String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javatopicpolicy;SharedAccessKey=Kk/4YJT6N0le4iXkGcuh/cwpvwhqIdhef+ASbA8UurI=;EntityPath=japatopic";
//            ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
//                    .connectionString(connectionString)
//                    .sender()
//                    .topicName("japatopic")
//                    .buildClient();
//
//
//            ServiceBusMessage message = new ServiceBusMessage(payloadJson);
//            message.setContentType("application/json");
//
//
//            senderClient.sendMessage(message);
//            senderClient.close();
//
//
//            return ResponseEntity.ok("Data Sent To Topic");
//        } catch (IOException | ServiceBusException ex) {
//            return ResponseEntity.ok(ex.toString());
//        }
//    }


//    @PostMapping("/SentToServiceBus")
//    public ResponseEntity<String> sendToServiceBus(@RequestBody GithubPayload payload){
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            String payloadJson = objectMapper.writeValueAsString(payload);
//
//
//
//            String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javatopicpolicy;SharedAccessKey=Kk/4YJT6N0le4iXkGcuh/cwpvwhqIdhef+ASbA8UurI=;EntityPath=japatopic";
//            ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
//                    .connectionString(connectionString)
//                    .sender()
//                    .topicName("japatopic")
//                    .buildClient();
//
//
//
//            ServiceBusMessage message = new ServiceBusMessage(payloadJson);
//            message.setContentType("application/json");
//
//
//
//            senderClient.sendMessage(message);
//            senderClient.close();
//
//
//
//            return ResponseEntity.ok("Data Sent To Topic");
//        } catch (IOException | ServiceBusException ex) {
//            return ResponseEntity.ok(ex.toString());
//        }
//    }


   // @PostMapping("/sentToServiceBus")

//    public ResponseEntity<String> sentToServiceBus(@RequestBody GithubPayload payload) {
//        // Your service bus logic here
//        return ResponseEntity.ok("Data Sent To Topic");
//    }
//    public ResponseEntity<String> sendToServiceBus(@RequestBody GithubPayload payload) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonData = objectMapper.readTree(String.valueOf(payload));
//            String commitId = jsonData.get("after").asText();
//
//            String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javatopicpolicy;SharedAccessKey=Kk/4YJT6N0le4iXkGcuh/cwpvwhqIdhef+ASbA8UurI=;EntityPath=japatopic";
//            ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
//                    .connectionString(connectionString)
//                    .sender()
//                    .topicName("japatopic")
//                    .buildClient();
//
//            ServiceBusMessage message = new ServiceBusMessage(String.valueOf(payload));
//            message.setContentType("application/json");
//
//            senderClient.sendMessage(message);
//            senderClient.close();
//
//            return ResponseEntity.ok("Data Sent To Topic");
//        } catch (IOException | ServiceBusException ex) {
//            return ResponseEntity.ok(ex.toString());
//        }
//    }

    @PostMapping("/post")
    public ResponseEntity<List<WeatherForecast>> createPost(@RequestBody WeatherForecast weatherForecast) {
        List<WeatherForecast> weatherForecasts = new ArrayList<>();
        weatherForecasts.add(weatherForecast);
        return ResponseEntity.ok(weatherForecasts);
    }

}

