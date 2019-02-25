package org.abigfish.rabbitmq.web;

import org.abigfish.rabbitmq.entity.User;
import org.abigfish.rabbitmq.sender.DirectSender;
import org.abigfish.rabbitmq.sender.FanoutSender;
import org.abigfish.rabbitmq.sender.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private FanoutSender fanoutSender;
	@Autowired
	private TopicSender topicSender;
	@Autowired
	private DirectSender directSender;

	@GetMapping("/testDirect")
	public void testDirect() {
		User user = new User();
		user.setId("1");
		user.setName("pwl");
		directSender.send(user);
	}

}
