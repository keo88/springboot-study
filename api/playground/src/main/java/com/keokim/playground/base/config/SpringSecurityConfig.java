package com.keokim.playground.base.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Getter
	@AllArgsConstructor
	enum WhitelistPath {

		INDEX("/"),
		HEALTH("/health-check"),
		LOGIN("/member/login"),
		LOGOUT("/member/logout");

		private final String path;

		public static String[] getPaths() {
			return Arrays.stream(WhitelistPath.values()).map(WhitelistPath::getPath).toArray(String[]::new);
		}
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize ->
			authorize.requestMatchers(WhitelistPath.getPaths()).permitAll()
				.anyRequest().authenticated()
		);

		return http.build();
	}
}
