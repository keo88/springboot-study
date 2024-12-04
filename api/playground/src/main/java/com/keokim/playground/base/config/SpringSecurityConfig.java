package com.keokim.playground.base.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
		LOGOUT("/member/logout"),
		CSS("/static/css/bootstrap.min.css");

		private final String path;

		public static String[] getPaths() {
			return Arrays.stream(WhitelistPath.values()).map(WhitelistPath::getPath).toArray(String[]::new);
		}
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize ->
			authorize
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers(WhitelistPath.getPaths()).permitAll()
				.anyRequest().authenticated()
		);

		return http.build();
	}
}
