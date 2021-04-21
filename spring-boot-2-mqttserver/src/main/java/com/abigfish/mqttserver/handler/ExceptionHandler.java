package com.abigfish.mqttserver.handler;

import org.springframework.stereotype.Component;

import com.iot.container.ExceptorAcceptor;

@Component
public class ExceptionHandler implements ExceptorAcceptor {
    public void accept(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }
}