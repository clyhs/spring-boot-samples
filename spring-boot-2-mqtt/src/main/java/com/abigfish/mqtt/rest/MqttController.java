/**
 * 
 */
package com.abigfish.mqtt.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abigfish.mqtt.gateway.MqttGateway;

import lombok.extern.slf4j.Slf4j;

/**
 * @date Apr 18, 2021 11:43:26 PM
 *
 * @author 大鱼
 *
 */
@RestController
@RequestMapping("/mqtt")
@Slf4j
public class MqttController {

	@Autowired
	MqttGateway mqttGateway;
	
	@GetMapping("/send")
    public String send(@RequestParam("topic") String topic,@RequestParam("data") String data) {
        log.info("开始发送mqtt消息,主题：{},消息：{}", topic, data);
        if (StringUtils.isNotBlank(topic)) {
            mqttGateway.sendToMqtt(topic, data);
        } else {
            mqttGateway.sendToMqtt(data);
        }
        log.info("发送mqtt消息完成,主题：{},消息：{}", topic, data);
        return "ok";
    }

}
