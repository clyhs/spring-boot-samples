package org.abigfish.jediscluster.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.JedisCluster;

@Service
public class RedisClientTemplate {
	
	private static final Logger LOGGER    = LoggerFactory.getLogger(RedisClientTemplate.class);
	 
    @Autowired
    private JedisCluster  jedisCluster;
 
    /**
     * 设置缓存
     * @param key    缓存key
     * @param value  缓存value
     */
    public void set(String key, String value) {
        jedisCluster.set(key, value);
        LOGGER.debug("RedisUtil:set cache key={},value={}", key, value);
    }
 
    /**
     * 设置缓存对象
     * @param key    缓存key
     * @param obj  缓存value
     */
    public <T> void setObject(String key, T obj , int expireTime) {
        jedisCluster.setex(key, expireTime, JSON.toJSONString(obj));
    }
 
    /**
     * 获取指定key的缓存
     * @param key---JSON.parseObject(value, User.class);
     */
    public String getObject(String key) {
        return jedisCluster.get(key);
    }
 
    /**
     * 判断当前key值 是否存在
     *
     * @param key
     */
    public boolean hasKey(String key) {
        return jedisCluster.exists(key);
    }
 
 
    /**
     * 设置缓存，并且自己指定过期时间
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    public void setWithExpireTime( String key, String value, int expireTime) {
        jedisCluster.setex(key, expireTime, value);
        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value, expireTime);
    }
 
 
    /**
     * 获取指定key的缓存
     * @param key
     */
    public String get(String key) {
        String value = jedisCluster.get(key);
        LOGGER.debug("RedisUtil:get cache key={},value={}",key, value);
        return value;
    }
 
    /**
     * 删除指定key的缓存
     * @param key
     */
    public void delete(String key) {
        jedisCluster.del(key);
        LOGGER.debug("RedisUtil:delete cache key={}", key);
    }
 

}
