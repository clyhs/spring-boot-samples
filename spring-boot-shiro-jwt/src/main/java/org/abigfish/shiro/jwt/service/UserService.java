package org.abigfish.shiro.jwt.service;

import org.abigfish.shiro.jwt.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public User findByUserName(String username){
		
		User u = new User();
		u.setId(1);
		u.setUsername("admin");
		u.setPassword("123456");
		
		if(username.equals("admin")){
			return u;
		}else{
			return null;
		}
	}

}
