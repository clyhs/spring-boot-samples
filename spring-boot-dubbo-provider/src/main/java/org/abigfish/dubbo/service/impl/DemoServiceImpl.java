package org.abigfish.dubbo.service.impl;

import org.abigfish.dubbo.service.DemoService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;


@Service(interfaceClass = DemoService.class)
@Component
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println(name);
		return "hello "+name;
	}

}
