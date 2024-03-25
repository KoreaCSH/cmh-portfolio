package com.choimyeongheon.portfolio.global.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class FormAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        String errorCode = "InvalidLoginRequest";

        if (exception instanceof BadCredentialsException) {
            errorCode = exception.getMessage();
        } else if (exception instanceof InsufficientAuthenticationException) {
            errorCode = "InvalidSecretKey";
        }

        setDefaultFailureUrl("/security-error?errorCode=" + errorCode);
        super.onAuthenticationFailure(request, response, exception);
    }
}
