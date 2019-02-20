package org.abigfish.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@RestController
public class App 
{
	@Autowired
	private RedisTemplate redisTemplate;
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    @GetMapping("/redis")
    public String getRedis(){
    	return (String) redisTemplate.opsForValue().get("springboot");
    }
}