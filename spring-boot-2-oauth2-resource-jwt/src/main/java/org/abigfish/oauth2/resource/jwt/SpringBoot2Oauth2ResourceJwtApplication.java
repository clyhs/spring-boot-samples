package org.abigfish.oauth2.resource.jwt;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class SpringBoot2Oauth2ResourceJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2Oauth2ResourceJwtApplication.class, args);
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasAuthority('role_admin')")
    public Object getUsers(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
        
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");

        System.out.println(token);

        return authentication;
    }

}
