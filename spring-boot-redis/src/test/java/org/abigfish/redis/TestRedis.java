package org.abigfish.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class TestRedis {
 
	@Autowired
    private RedisTemplate redisTemplate;
	
	@Test
	public void setTest(){
		redisTemplate.opsForValue().set("springboot", "hh");
	}
}
