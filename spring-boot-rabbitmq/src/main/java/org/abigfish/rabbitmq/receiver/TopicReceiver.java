package org.abigfish.rabbitmq.receiver;
 

import org.abigfish.rabbitmq.config.RabbitConfig;
import org.abigfish.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
 
@Component
public class TopicReceiver {
    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE1)
    @RabbitHandler
    public void receiveTopic1(User user) {
        System.out.println("【receiveTopic1监听到消息】" + user.toString());
    }
    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE2)
    @RabbitHandler
    public void receiveTopic2(User user) {
        System.out.println("【receiveTopic2监听到消息】" + user.toString());
    }
}
