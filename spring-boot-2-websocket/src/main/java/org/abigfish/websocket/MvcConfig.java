package org.abigfish.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 这里之所以多了一"/",是为了解决打jar时访问不到问题
        registry.addResourceHandler("/index.html").addResourceLocations("/index.html","classpath:/index.html");
    }
}