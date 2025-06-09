package com.keokim.ncphw.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(authorize ->
			authorize
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers(WhitelistPath.getPaths()).permitAll()
				.anyRequest().authenticated()
		);

		http.authenticationProvider(authenticationProvider());

		http.formLogin(form -> form
			.loginPage("/members/login")
			.usernameParameter("username")
			.defaultSuccessUrl("/", true)
			.permitAll()
		);

		http.logout(logout -> logout
			.logoutUrl("/members/logout")
			.logoutSuccessUrl("/members/login")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
		);

		return http.build();
	}

	@Getter
	enum WhitelistPath {

		HEALTH_CHECK("/health-check"),
		MEMBERS("/members/**");

		private final String path;

		WhitelistPath(String path) {
			this.path = path;
		}

		public static String[] getPaths() {
			return Arrays.stream(WhitelistPath.values()).map(WhitelistPath::getPath).toArray(String[]::new);
		}
	}
} 