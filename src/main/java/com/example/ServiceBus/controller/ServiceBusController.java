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

    String str = "Sindhujha";

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
    public ResponseEntity<String> sendToServiceBus(@RequestBody GithubPayload payload) {
        return githubPayloadService.sendPayloadToServiceBus(payload);
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

