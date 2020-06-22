package com.epam.jdi.bookstore.service.impl;

import com.epam.jdi.bookstore.exception.InvalidCredentialsException;
import com.epam.jdi.bookstore.model.security.Token;
import com.epam.jdi.bookstore.model.security.Credentials;
import com.epam.jdi.bookstore.security.JwtTokenProvider;
import com.epam.jdi.bookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Token authenticateUser(Credentials credentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword())
            );
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            String jwt = tokenProvider.generateToken(authentication);
            return Token.builder().accessToken(jwt).build();
        } catch (BadCredentialsException | ParseException e) {
            throw new InvalidCredentialsException("Invalid login or password");
        }
    }
}
