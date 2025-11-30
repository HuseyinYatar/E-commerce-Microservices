package com.ecommerce.OrderService.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Value("${PLACE_ORDER_TOPIC}")
    private String place_order;

    @Bean
    NewTopic KafkaTopic() {
    return TopicBuilder
            .name(place_order)
            .build();
    }
}
