package org.abigfish.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
	public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /**
     * 创建webSocket配置对象
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
