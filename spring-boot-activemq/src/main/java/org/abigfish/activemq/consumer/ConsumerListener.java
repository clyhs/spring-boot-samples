package org.abigfish.activemq.consumer;

import org.abigfish.activemq.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumerListener {

	private static final Logger log = LoggerFactory.getLogger(ConsumerListener.class);

	@JmsListener(destination = "app.log")
	@Transactional(rollbackFor = { Exception.class })
	public void reciver(Message obj) {
		log.info("消费者:" + "--message : " + obj);
	}

	@JmsListener(destination = "sms.topic")
	public void receiveTopic1(String text) {
		System.out.println("receiveTopic1接收到Topic消息 : " + text);
	}

	@JmsListener(destination = "sms.topic")
	public void receiveTopic2(String text) {
		System.out.println("receiveTopic2接收到Topic消息 : " + text);
	}
}