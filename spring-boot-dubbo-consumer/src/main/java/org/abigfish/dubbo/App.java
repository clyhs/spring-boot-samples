package org.abigfish.dubbo;

import org.abigfish.dubbo.service.DemoConsumerService;
import org.abigfish.dubbo.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

/**
 * Hello world!
 *
 */
@EnableDubboConfiguration
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext run = SpringApplication.run(App.class,args);
    	
    	DemoConsumerService s = run.getBean(DemoConsumerService.class);
        System.out.println(s.sayHello());
    }
}
