package org.abigfish.oauth2.jwt;

import org.abigfish.oauth2.jwt.model.entity.User;
import org.abigfish.oauth2.jwt.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecurityOauth2Test {
	
	@Autowired
	private UserRepository ur;

	@Test
	public void updateUserPassword(){
		User user = ur.findByUsername("admin");
		System.out.println(user.getPassword());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// 加密
		String encodedPassword = passwordEncoder.encode("password");
		user.setPassword(encodedPassword);
		
		ur.save(user);

	}
}
