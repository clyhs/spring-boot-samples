package org.abigfish.activemq.service.impl;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

import org.abigfish.activemq.message.Message;
import org.abigfish.activemq.service.IMessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProviderImpl implements IMessageProvider{
	
	private static final Logger log = LoggerFactory.getLogger(MessageProviderImpl.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue logQueue;

    @Resource
    private Topic topic;

	@Override
	public void sender(Message obj) {
		// TODO Auto-generated method stub
		log.debug("发送消息--"+obj);
        jmsMessagingTemplate.convertAndSend(logQueue, obj);
	}
	

	public void sendTopic(String msg) {
		System.out.println("发送Topic消息内容 :"+msg);
		jmsMessagingTemplate.convertAndSend(this.topic, msg);
	}

}
