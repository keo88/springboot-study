package com.keokim.ncphw.util;

import com.keokim.ncphw.domain.Member;
import com.keokim.ncphw.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserLoggingInterceptor implements HandlerInterceptor {

    private final LogService logService;

    public UserLoggingInterceptor(LogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        Member member = ((Member)request.getSession().getAttribute("user"));
        if (member != null) {
            logService.logApi(request.getServletPath(), member);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
