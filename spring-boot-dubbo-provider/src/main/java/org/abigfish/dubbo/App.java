package org.abigfish.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDubboConfiguration
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class,args);
    }
}
