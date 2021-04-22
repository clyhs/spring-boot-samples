package org.abigfish.activemq.service;

import org.abigfish.activemq.message.Message;

public interface IMessageProvider {
	
	public void sender(Message obj);

	public void sendTopic(String msg);
}
