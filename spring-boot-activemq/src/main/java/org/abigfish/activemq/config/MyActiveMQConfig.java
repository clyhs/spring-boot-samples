package org.abigfish.activemq.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyActiveMQConfig {

    @Bean
    public Queue logQueue() {
        return new ActiveMQQueue("app.log");
    }
}