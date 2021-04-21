package com.abigfish.mqttserver.auth;

import com.iot.container.AuthencationSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MqttAuthencation implements AuthencationSession {
    public boolean auth(String username, String password) {
    	log.debug(username);
        return true;
    }
}
