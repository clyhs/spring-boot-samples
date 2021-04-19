/**
 * 
 */
package com.abigfish.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.abigfish.mqtt.handler.MyMqttMessageHandler;

/**
 * @date Apr 18, 2021 11:10:29 PM
 *
 * @author 大鱼
 *
 */
@Configuration
@EnableConfigurationProperties(MqttProperties.class)
public class MqttConfig {
	
	MqttProperties mqttProperties;
	
	 private MqttPahoMessageDrivenChannelAdapter adapter;
	
	public MqttConfig(MqttProperties mqttProperties) {
		this.mqttProperties = mqttProperties;
	}
	
	/**
	 * 配置DefaultMqttPahoClientFactory
	 * @return
	 */
	@Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setKeepAliveInterval(30);
        options.setConnectionTimeout(10);
        options.setAutomaticReconnect(true);
        options.setServerURIs(new String[]{mqttProperties.getUrl()});
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        factory.setConnectionOptions(options);
        return factory;
    }
	
	//mqtt入站消息处理配置
	@Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }
	
	@Bean
    public MessageProducer inbound() {
        if (adapter == null) {
            adapter = new MqttPahoMessageDrivenChannelAdapter(
                    mqttProperties.getClientId() + "_inbound1",
                    mqttClientFactory(),
                    "/UL/*/property/POST", "/UL/*/event/*", "/UL/22R201512DB21188/property/POST");
        }
        adapter.setCompletionTimeout(3000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MyMqttMessageHandler();
    }
    
    //配置Outbound出站，出站通道适配器
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(mqttProperties.getClientId(), mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(mqttProperties.getDefaultTopic());
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

}
