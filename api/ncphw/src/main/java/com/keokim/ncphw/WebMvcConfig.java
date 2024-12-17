package com.keokim.ncphw;

import com.keokim.ncphw.util.AuthInterceptor;
import com.keokim.ncphw.util.UserLoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;
    private final UserLoggingInterceptor userLoggingInterceptor;

    public WebMvcConfig(AuthInterceptor authInterceptor, UserLoggingInterceptor userLoggingInterceptor) {
        this.authInterceptor = authInterceptor;
        this.userLoggingInterceptor = userLoggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns("/members/*", "/health-check");
        registry.addInterceptor(userLoggingInterceptor).addPathPatterns("/**");
    }
}
