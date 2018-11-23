package org.abigfish.dubbo.web;

import org.abigfish.dubbo.service.DemoConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	DemoConsumerService dcs;
	
	@ResponseBody
	@GetMapping("/hello")
	public String sayHello(){
		return dcs.sayHello();
	}

}
