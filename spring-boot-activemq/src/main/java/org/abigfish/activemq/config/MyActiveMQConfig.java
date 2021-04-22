package org.abigfish.activemq.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyActiveMQConfig {

    @Bean
    public Queue logQueue() {
        return new ActiveMQQueue("app.log");
    }
    

	@Bean
	public Topic topic() {
		return new ActiveMQTopic("sms.topic");
	}
}