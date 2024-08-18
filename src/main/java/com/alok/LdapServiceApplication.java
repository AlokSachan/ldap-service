package com.alok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LdapServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdapServiceApplication.class, args);
	}

//	@Bean
//	public String generateBcryptPassword(){
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		String bcrypt = bCryptPasswordEncoder.encode("alokspassword");
//		System.out.println("password " + bcrypt);
//		return bcrypt;
//	}

}
