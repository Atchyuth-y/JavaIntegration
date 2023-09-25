//package com.example.ServiceBus.config;
//
////import com.azure.messaging.servicebus.ServiceBusClientBuilder;
////import com.azure.messaging.servicebus.ServiceBusSenderClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConfigurationProperties
//public class AzureServiceBusConfig {
//
//    //@Value("Endpoint=sb://javaservicebus.servicebus.windows.net/;SharedAccessKeyName=javatopicpolicy;SharedAccessKey=Kk/4YJT6N0le4iXkGcuh/cwpvwhqIdhef+ASbA8UurI=;EntityPath=japatopic")
//    private String connectionString;
//
//   // @Value("japatopic")
//    private String topicName;
//
//    public String getConnectionString() {
//        return connectionString;
//    }
//
//    public void setConnectionString(String connectionString) {
//        this.connectionString = connectionString;
//    }
//
//    public String getTopicName() {
//        return topicName;
//    }
//
//    public void setTopicName(String topicName) {
//        this.topicName = topicName;
//    }
//
//    //    @Bean
////    public ServiceBusSenderClient serviceBusSender() {
////        // Create and configure the ServiceBusSenderClient using the connection string and topic name
////        return new ServiceBusClientBuilder()
////                .connectionString(connectionString)
////                .sender()
////                .topicName(topicName)
////                .buildClient();
////    }
//}
//
