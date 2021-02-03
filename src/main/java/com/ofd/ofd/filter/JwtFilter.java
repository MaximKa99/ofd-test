package com.ofd.ofd.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofd.ofd.controller.ExceptionController;
import com.ofd.ofd.service.JwtProvider;
import com.ofd.ofd.service.UserService;
import com.ofd.ofd.view.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class JwtFilter extends GenericFilterBean {
    private JwtProvider provider;
    private UserService userService;
    private ExceptionController exceptionController;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public JwtFilter(JwtProvider provider, UserService userService, ExceptionController exceptionController) {
        this.provider = provider;
        this.userService = userService;
        this.exceptionController = exceptionController;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = getTokenFromRequest(request);
        if (token != null && provider.validateToken(token)) {
            String login = provider.getLoginFromToken(token);
            UserDetails userDetails;
            try {
                userDetails = userService.loadUserByUsername(login);
            } catch (Exception e) {
                //Это нужно чтобы отлавливать исключения, брошенные из фильтра, т к AdviceController их отсюда не отлавливает
                ResponseEntity<ErrorResponse> entity = exceptionController.handle(e);
                response.getWriter().write(mapper.writeValueAsString(entity.getBody()));
                ((HttpServletResponse)response).setStatus(entity.getStatusCodeValue());
                return;
            }
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }
    
    private String getTokenFromRequest(ServletRequest request) {
        Cookie[] cookies = ((HttpServletRequest)request).getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
