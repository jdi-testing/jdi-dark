package com.epam.jdi.bookstore.service;

import com.epam.jdi.bookstore.model.user.Address;
import com.epam.jdi.bookstore.model.user.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> getUsers();

    User updateUser(Long id, User user);

    User getUserById(Long id);

    User getUserByEmail(String email);

    void restoreUser(Long id);

    void deleteUserById(Long id);

    void createAddress(Long userId, Address address);

    List<Address> getUserAddresses(Long userId);

    void updateUserAddress(Long userId, Long addressId, Address address);

    void deleteUserAddress(Long userId, Long addressId);
}
