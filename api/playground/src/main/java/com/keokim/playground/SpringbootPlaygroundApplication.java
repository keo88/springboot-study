package com.keokim.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.keokim.playground.base.alias.User;

@SpringBootApplication
public class SpringbootPlaygroundApplication {

	public static void main(String[] args) {
		User user = new User();
		user.setId(1L);
		System.out.println(user.getId());

		SpringApplication.run(SpringbootPlaygroundApplication.class, args);
	}

}
