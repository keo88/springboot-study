package com.keokim.ncphw.util;

import com.keokim.ncphw.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        Member member = ((Member)request.getSession().getAttribute("user"));
        if (member == null) {
            response.sendRedirect("/members/login");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
