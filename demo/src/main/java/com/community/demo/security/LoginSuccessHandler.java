package com.community.demo.security;

import com.community.demo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    RedirectStrategy redirectStrategy =
            new DefaultRedirectStrategy();

    RequestCache requestCache =
            new HttpSessionRequestCache();

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        userService.lastLoginUpdate(authentication.getName());

        HttpSession ses = request.getSession();
        if(ses == null){
            return;
        }else {
            ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        redirectStrategy.sendRedirect(request, response,
                savedRequest != null ? savedRequest.getRedirectUrl(): "/board/list");
    }
}
