package com.epam.jdi.bookstore.service.impl;

import com.epam.jdi.bookstore.exception.AlreadyExistException;
import com.epam.jdi.bookstore.exception.NotFoundException;
import com.epam.jdi.bookstore.model.user.Address;
import com.epam.jdi.bookstore.model.user.Role;
import com.epam.jdi.bookstore.model.user.User;
import com.epam.jdi.bookstore.repository.user.AddressRepository;
import com.epam.jdi.bookstore.repository.user.UserRepository;
import com.epam.jdi.bookstore.service.RoleService;
import com.epam.jdi.bookstore.service.UserService;
import com.epam.jdi.bookstore.utils.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private PasswordEncoder bCryptPasswordEncoder;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder bCryptPasswordEncoder,
                           RoleService roleService, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
        this.addressRepository = addressRepository;
    }

    @Override
    public void createUser(User user) {
        checkIfUserExists(user);
        checkAndSetRoles(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userToUpdate = getUserById(id);
        user.setId(userToUpdate.getId());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = findUserById(id);
        if (!user.isEnabled()) {
            throw new NotFoundException("User with ID '" + id + "' not found");
        } else {
            user.setEnabled(false);
            user.setPassword(userRepository.findUserPassword(id));
            userRepository.saveAndFlush(user);
        }
    }

    @Override
    public void restoreUser(Long id) {
        User userToRestore = findUserById(id);
        if (userToRestore.isEnabled()) {
            throw new AlreadyExistException(String.format("User with ID %d is already active and doesn't need to be restored", id));
        } else {
            userToRestore.setEnabled(true);
            userToRestore.setPassword(userRepository.findUserPassword(id));
            userRepository.save(userToRestore);
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return findUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException(String.format("User with email '%s' not found!", email)));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("User with ID '%d' not found!", id)));
    }

    private void checkIfUserExists(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(e -> {
            throw new AlreadyExistException(String.format("User with email '%s' already exists!", user.getEmail()));
        });
        if (user.getId() != null) {
            userRepository.findById(user.getId()).ifPresent(e -> {
                throw new AlreadyExistException(String.format("User with ID '%d' already exists!", user.getId()));
            });
        }
    }

    private void checkAndSetRoles(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Collections.singletonList(roleService.getRoleByName(Roles.USER)));
            return;
        }
        List<Role> rolesToSet = user.getRoles().stream()
                .map(r -> roleService.getRole(r.getId())).collect(Collectors.toList());
        user.setRoles(rolesToSet);
    }

    @Override
    public void createAddress(Long userId, Address address) {
        address.setUser(getUserById(userId));
        System.out.println(address);
        addressRepository.save(address);
    }

    @Override
    public List<Address> getUserAddresses(Long userId) {
        User user = getUserById(userId);
        return addressRepository.getAddressByUser(user);
    }

    @Override
    public void updateUserAddress(Long userId, Long addressId, Address address) {
        //TODO Need Implementation
    }

    @Override
    public void deleteUserAddress(Long userId, Long addressId) {
        //TODO Need Implementation
    }

}
