package com.example.ServiceBus.controller;

import com.example.ServiceBus.model.GithubPayload;
import com.example.ServiceBus.model.WeatherForecast;
import com.example.ServiceBus.service.GithubPayloadService;
import com.example.ServiceBus.service.ReceiverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    GithubPayloadService githubPayloadService;

    @Autowired
    ReceiverService receiverService;

    Logger logger = LoggerFactory.getLogger(ServiceBusController.class);

    //String str = "Sindhujha";

    private static final String[] Summaries = {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
    };

    @GetMapping("/view")
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
            // Serialize the GithubPayload object to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String payloadJsonString = objectMapper.writeValueAsString(payloadJson);



            // Your code for sending data to Azure Service Bus
            String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javaqueuepolicy;SharedAccessKey=Q5VJN3BXXXc5ZICuMw6uw+mlXyR7z45w9+ASbHCfMIo=;EntityPath=javaqueue";
            ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                    .connectionString(connectionString)
                    .sender()
                    .queueName("javaqueue")
                    .buildClient();



            // Create a ServiceBusMessage and set its content to the serialized JSON
            ServiceBusMessage message = new ServiceBusMessage(payloadJsonString);
            message.setContentType("application/json");



            // Send the message
            senderClient.sendMessage(message);



            // Close the sender client
            senderClient.close();



            // Create a JSON response
            Map<String, String> response = new HashMap<>();
            response.put("message", "Data Sent To Queue");

            logger.info("Received Payload: " + payloadJson);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            // Handle the error and return an error response
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred: " + ex.getMessage());



            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/receive")
    public void receiveMessage(){
        receiverService.receiveMessage();
    }

    @PostMapping("/post")
    public ResponseEntity<List<WeatherForecast>> createPost(@RequestBody WeatherForecast weatherForecast) {
        List<WeatherForecast> weatherForecasts = new ArrayList<>();
        weatherForecasts.add(weatherForecast);
        return ResponseEntity.ok(weatherForecasts);
    }

    @PostMapping("/send")
    public void sendMessage(){
        githubPayloadService.sendMessage();
    }

}

