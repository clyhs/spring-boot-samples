/**
 * 
 */
package com.abigfish.mqtt.handler;

import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @date Apr 18, 2021 11:22:23 PM
 *
 * @author 大鱼
 *
 */
//@Component
@Slf4j
public class MyMqttMessageHandler implements MessageHandler {

	/* (non-Javadoc)
	 * @see org.springframework.messaging.MessageHandler#handleMessage(org.springframework.messaging.Message)
	 */
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		// TODO Auto-generated method stub
		String topic = String.valueOf(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC));
        String payload = String.valueOf(message.getPayload());
        log.info("接收到 mqtt消息，主题:{} 消息:{}", topic, payload);

	}

}
