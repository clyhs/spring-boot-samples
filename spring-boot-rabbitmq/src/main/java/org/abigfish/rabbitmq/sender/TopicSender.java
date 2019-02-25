package org.abigfish.rabbitmq.sender;
 

import org.abigfish.rabbitmq.config.RabbitConfig;
import org.abigfish.rabbitmq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
 
    // 第一个参数：TopicExchange名字
    // 第二个参数：Route-Key
    // 第三个参数：要发送的内容
    public void send(User user) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,"abigfish.message", user);
        this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "abigfish.xxxxx", user);
    }
}
