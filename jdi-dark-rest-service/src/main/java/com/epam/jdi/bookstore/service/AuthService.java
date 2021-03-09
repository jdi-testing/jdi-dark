package com.epam.jdi.bookstore.service;

import com.epam.jdi.bookstore.model.security.Credentials;
import com.epam.jdi.bookstore.model.security.Token;

public interface AuthService {

    Token authenticateUser(Credentials credentials);

}
