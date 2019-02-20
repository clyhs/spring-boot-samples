package org.abigfish.rediscluster.service;

import org.abigfish.rediscluster.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User getUser(String id) {
		log.info(id+"进入实现类获取数据！");
		User user = new User();
		user.setId(id);
		user.setName("abigfish");
		user.setPassword("123456");
		return user;
	}

	@Override
	public void deleteUser(String id) {
		log.info(id+"进入实现类删除数据！");
	}

}