package com.abigfish.nettyclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abigfish.nettyclient.message.TcpMessage;

import lombok.Value;

@Configuration
public class HeartBeatConfig {


    @Bean(value = "heartBeat")
    public TcpMessage heartBeat(){
        return new TcpMessage(1l,"ping") ;
    }
}