package com.keokim.ncphw;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.keokim.ncphw.util.UserLoggingInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final UserLoggingInterceptor userLoggingInterceptor;

	public WebMvcConfig(UserLoggingInterceptor userLoggingInterceptor) {
		this.userLoggingInterceptor = userLoggingInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userLoggingInterceptor).addPathPatterns("/**");
	}
}
