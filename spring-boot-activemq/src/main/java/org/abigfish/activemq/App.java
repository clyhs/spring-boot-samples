package org.abigfish.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJms //ActiveMQ
public class App{

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
