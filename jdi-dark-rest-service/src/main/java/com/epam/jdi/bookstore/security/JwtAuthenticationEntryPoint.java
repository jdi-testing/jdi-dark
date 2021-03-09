package com.epam.jdi.bookstore.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.jdi.bookstore.BookstoreApiApplication.logger;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
             AuthenticationException authException) throws IOException, ServletException {
        logger.info("unauthorized request : {} {}", request.getMethod(), request.getRequestURL());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized");
    }
}
