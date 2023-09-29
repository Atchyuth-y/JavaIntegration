package com.example.ServiceBus.service;

import com.azure.messaging.servicebus.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class ReceiverService {


    //Receiver logic for the payload
    static  String connectionString = "Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javaqueuepolicy;SharedAccessKey=Q5VJN3BXXXc5ZICuMw6uw+mlXyR7z45w9+ASbHCfMIo=;EntityPath=javaqueue";
    static  String queueName = "javaqueue";

    public void receiveMessage() {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(this::processMessage)
                .processError(context -> processError(context, countDownLatch))
                .buildProcessorClient();

        System.out.println("Starting message receiver...");
        processorClient.start();

        try {
            // Add any desired logic here, e.g., sleep for a period or perform other tasks.
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Stopping message receiver...");
        processorClient.close();
    }

    private void processError(ServiceBusErrorContext context, CountDownLatch countDownLatch) {
        // Add error handling logic here, if needed.
    }

    private void processMessage(ServiceBusReceivedMessageContext context) {
        ServiceBusReceivedMessage msg = context.getMessage();
        System.out.printf("Processing message. Session: %s, Sequence #: %s. Contents: %s%n",
                msg.getMessageId(), msg.getSequenceNumber(), msg.getBody());
    }
}