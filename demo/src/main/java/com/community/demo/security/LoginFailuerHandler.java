package com.community.demo.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
public class LoginFailuerHandler implements AuthenticationFailureHandler {

    RedirectStrategy redirectStrategy =
            new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        String LoginFailedEmail = request.getParameter("email");

        if(exception instanceof BadCredentialsException) {
            errorMessage = "email/password가 일치하지 않습니다.";
        }else {
            errorMessage = "관리자에게 문의해주세요.";
        }

        log.info(">>> LoginFailedEmail >> {}", LoginFailedEmail);
        log.info(">>> errorMessage >> {}", errorMessage);

        String url = "/user/login";
        request.getSession().setAttribute("email", LoginFailedEmail);
        request.getSession().setAttribute("errMsg",errorMessage);

        redirectStrategy.sendRedirect(request, response, url);
    }


}
