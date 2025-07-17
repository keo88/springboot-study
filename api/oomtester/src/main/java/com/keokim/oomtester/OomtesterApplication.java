package com.keokim.oomtester;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OomtesterApplication {

	public static void main(String[] args) {

		List<byte[]> list = new ArrayList<>();
		while (true) {
			list.add(new byte[1024 * 1024]);
		}

		// SpringApplication.run(OomtesterApplication.class, args);
	}

}
