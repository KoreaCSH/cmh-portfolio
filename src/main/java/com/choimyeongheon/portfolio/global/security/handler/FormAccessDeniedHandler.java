package com.choimyeongheon.portfolio.global.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class FormAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        // 추후 ADMIN 이 아닌 계정이 추가되었을 때를 대비한 AccessDeniedHandler
        String errorMessage = "NoAuthorization";
        String deniedUrl = "/error-alert?errorMessage=" + errorMessage;
        response.sendRedirect(deniedUrl);
    }
}
