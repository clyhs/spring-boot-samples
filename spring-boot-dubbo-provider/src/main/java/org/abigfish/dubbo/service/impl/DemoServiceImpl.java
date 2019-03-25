package org.abigfish.dubbo.service.impl;

import org.abigfish.dubbo.api.Foo;
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

	@Override
	public Foo getFoo() {
		// TODO Auto-generated method stub
		Foo f = new Foo();
		f.setField1("11111111");
		f.setField2("aaaaaaaa");
		f.setField3("AAAAAAAA");
		f.setField4("!@#@$@$$");
		f.setField5("我我我我我我");
		f.setField6("22222222");
		f.setField7("bbbbbbbb");
		f.setField8("他他他他他他");
		
		return f;
	}

}
