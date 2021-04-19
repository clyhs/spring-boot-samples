/**
 * 
 */
package com.abigfish.nettyserver;

import java.net.InetSocketAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abigfish.nettyserver.server.NettyServer;

/**
 * @date Apr 19, 2021 10:49:51 PM
 *
 * @author 大鱼
 *
 */
@SpringBootApplication
public class NettyApplication {

	public static void main( String[] args )
    {
    	SpringApplication.run(NettyApplication.class, args);
    	NettyServer nettyServer = new NettyServer();
        nettyServer.start(new InetSocketAddress("127.0.0.1", 8090));
    }
}
