package org.abigfish.ali.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

/**
 * 
 * @author abigfish
 *
 * @date 2018年12月10日
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class App 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(App.class, args);
    }
}
