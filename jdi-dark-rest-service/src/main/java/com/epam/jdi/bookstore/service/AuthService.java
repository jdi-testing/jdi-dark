package com.epam.jdi.bookstore.service;

import com.epam.jdi.bookstore.model.security.Token;
import com.epam.jdi.bookstore.model.security.Credentials;

public interface AuthService {

    Token authenticateUser(Credentials credentials);

}
