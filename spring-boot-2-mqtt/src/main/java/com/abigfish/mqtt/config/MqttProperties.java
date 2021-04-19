package com.abigfish.mqtt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private String clientId;

    private String url;

    private String username;

    private String password;

    private String defaultTopic;

}