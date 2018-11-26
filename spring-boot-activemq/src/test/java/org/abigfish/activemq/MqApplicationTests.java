package org.abigfish.activemq;
 


import org.abigfish.activemq.message.Message;
import org.abigfish.activemq.service.IMessageProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
 
import java.util.List;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqApplicationTests {
 
	@Autowired
	private IMessageProvider messageProvider;
	
	@Test
	public void sender(){
		Message obj = new Message();
		obj.setCode(1);
		obj.setMsg("测试");
		messageProvider.sender(obj);
	}
	
 
}
