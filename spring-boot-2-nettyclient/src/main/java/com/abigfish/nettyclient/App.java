package com.abigfish.nettyclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abigfish.nettyclient.client.NettyClient;



/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    	//启动netty客户端
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();
    }
}
