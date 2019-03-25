package org.abigfish.dubbo.service;

import org.abigfish.dubbo.api.Foo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class DemoConsumerService {
	
	@Reference
    DemoService demoService;

    public String sayHello() {
        String name="abigfish";
        return demoService.sayHello(name);
    }
    
    public Foo getFoo(){
    	return demoService.getFoo();
    }

}
