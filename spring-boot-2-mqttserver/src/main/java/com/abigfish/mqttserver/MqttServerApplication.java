/**
 * 
 */
package com.abigfish.mqttserver;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;



/**
 * @date Apr 21, 2021 10:35:12 PM
 *
 * @author 大鱼
 *
 */
@SpringBootApplication
@ComponentScan(basePackages= {"com.iot","com.abigfish"})
public class MqttServerApplication {

	public static void main(String[] args) {
        //SpringApplication.run(MqttServerApplication.class, args);
		ConfigurableApplicationContext run = run(MqttServerApplication.class, args);
    }
}
