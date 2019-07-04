package org.abigfish.oauth2.authorization.jwt.service;

import java.util.HashMap;
import java.util.Map;

import org.abigfish.oauth2.authorization.jwt.entity.User;
import org.abigfish.oauth2.authorization.jwt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String input) {
		User user = null;

		if (input.contains("@"))
			user = userRepository.findByEmail(input);
		else
			user = userRepository.findByUsername(input);

		if (user == null)
			throw new BadCredentialsException("Bad credentials");

		new AccountStatusUserDetailsChecker().check(user);

		return user;
	}

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encode = passwordEncoder.encode("password");
		
		log.info("加密后的密码:" + encode);
		log.info("bcrypt密码对比:" + passwordEncoder.matches("password", encode));
		String md5Password = "{MD5}{G8LVxbkApFJ9f9v3VAn5MLH80UvCBJlZJkLEV+f+OlQ=}bd97cc2285d9a9d3423ab3a957abc0a1";// MD5加密前的密码为:password
		
		log.info("MD5密码对比:" + passwordEncoder.matches("password", md5Password));
		
		
		Map<String, PasswordEncoder> encoders = new HashMap();
		encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
		PasswordEncoder passwordEncoder2 = new DelegatingPasswordEncoder("MD5", encoders);
		String encode2 = passwordEncoder2.encode("password");
		log.info("加密后的密码:" + encode2);

	}

}
