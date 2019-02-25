package org.abigfish.rabbitmq.sender;
 

import org.abigfish.rabbitmq.config.RabbitConfig;
import org.abigfish.rabbitmq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
 
    public void send(User user) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "", user);
    }
}
