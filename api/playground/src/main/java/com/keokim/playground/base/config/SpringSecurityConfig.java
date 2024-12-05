package com.keokim.playground.base.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

	@Getter
	@AllArgsConstructor
	enum WhitelistPath {

		INDEX("/"),
		HEALTH("/health-check"),
		LOGIN("/member/login"),
		NEW("/member/new");

		private final String path;

		public static String[] getPaths() {
			return Arrays.stream(WhitelistPath.values()).map(WhitelistPath::getPath).toArray(String[]::new);
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(authorize ->
			authorize
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers(WhitelistPath.getPaths()).permitAll()
				.requestMatchers("/member/**").authenticated()
				.anyRequest().authenticated()
		);

		http.formLogin(form -> form
			.loginPage("/member/login")
			.usernameParameter("name")
		);

		return http.build();
	}
}
