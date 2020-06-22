package com.epam.jdi.bookstore.controller.user;

import com.epam.jdi.bookstore.model.security.Token;
import com.epam.jdi.bookstore.service.RoleService;
import com.epam.jdi.bookstore.controller.UserApi;
import com.epam.jdi.bookstore.model.security.Credentials;
import com.epam.jdi.bookstore.model.user.Address;
import com.epam.jdi.bookstore.model.user.User;
import com.epam.jdi.bookstore.service.AuthService;
import com.epam.jdi.bookstore.service.UserService;
import com.epam.jdi.bookstore.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class UserController implements UserApi {

    private final UserService userService;
    private final AuthService authService;
    private final RoleService roleService;
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public UserController(UserService userService, AuthService authService, RoleService roleService,
                          CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.authService = authService;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public ResponseEntity<User> registerUser(User userToRegister) {
        userService.createUser(userToRegister);
        User user = userService.getUserByEmail(userToRegister.getEmail());
        URI location = URI.create(String.format("/%s", user.getId()));
        return created(location).body(user);
    }

    @Override
    public ResponseEntity<Token> authenticateUser(Credentials credentials) {
        return ok(authService.authenticateUser(credentials));
    }

    @Override
    public ResponseEntity<User> updateUser(Long id, User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ok(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return ok(userService.getUserByEmail(email));
    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ok().build();
    }

    @Override
    public ResponseEntity<Void> restoreUser(@PathVariable Long id) {
        userService.restoreUser(id);
        return ok().build();
    }

    @Override
    public ResponseEntity<List<Address>> getAddresses(Long user_id) {
        List<Address> addresses = userService.getUserAddresses(user_id);
        return ResponseEntity.ok(addresses);
    }

    @Override
    public ResponseEntity<Void> addAddress(Long user_id, Address address) {
        userService.createAddress(user_id, address);
        URI location = URI.create(String.format("/%s", address.getId()));
        return created(location).build();
    }
}
