package org.abigfish.oauth2.resource.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBoot2Oauth2ResourceJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2Oauth2ResourceJwtApplication.class, args);
	}
	
	@RequestMapping(value = "/users")
	@PreAuthorize("hasRole('ROLE_USER')")
    public String success() {
        return "SUCCESS";
    }

}
