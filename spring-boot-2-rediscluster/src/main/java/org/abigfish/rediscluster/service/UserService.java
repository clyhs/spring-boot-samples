package org.abigfish.rediscluster.service;

import org.abigfish.rediscluster.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface UserService {
	
	@Cacheable(key="'user_'+#id",value="'user'+#id")
	User getUser(String id);
	
	@CacheEvict(key="'user_'+#id", value="users", condition="#id!=1")
	void deleteUser(String id);

}