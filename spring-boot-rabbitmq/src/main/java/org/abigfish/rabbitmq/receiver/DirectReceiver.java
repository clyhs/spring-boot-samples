package org.abigfish.rabbitmq.receiver;
 

import org.abigfish.rabbitmq.config.RabbitConfig;
import org.abigfish.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
 
@Component
public class DirectReceiver {
    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE1)
    @RabbitHandler
    public void receiveDirect1(User user) {
 
        System.out.println("【receiveDirect1监听到消息】" + user);
    }
 
    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE2)
    @RabbitHandler
    public void receiveDirect2(User user) {
 
        System.out.println("【receiveDirect2监听到消息】" + user);
    }
}
