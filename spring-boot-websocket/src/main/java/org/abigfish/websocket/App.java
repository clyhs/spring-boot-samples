package org.abigfish.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@ComponentScan(basePackages ="org.abigfish.websocket")
@SpringBootApplication
@EnableScheduling
public class App {

    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);
    }
}