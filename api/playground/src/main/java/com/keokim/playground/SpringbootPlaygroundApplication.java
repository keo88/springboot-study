package com.keokim.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.keokim.playground.base.alias.User;
import com.keokim.playground.base.alias.UserRecord;

@SpringBootApplication
public class SpringbootPlaygroundApplication {

	public static void main(String[] args) {
		User user = new User();
		UserRecord two = new UserRecord(2L, "two");
		user.setId(1L);
		System.out.println(user.getId());
		System.out.println(two.name());

		SpringApplication.run(SpringbootPlaygroundApplication.class, args);
	}

}
