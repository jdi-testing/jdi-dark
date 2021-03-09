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

import java.util.List;

import static com.epam.jdi.tools.LinqUtils.map;
import static java.util.Collections.singletonList;

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
        user.password = bCryptPasswordEncoder.encode(user.password);
        user.enabled = true;
        userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userToUpdate = getUserById(id);
        user.id = userToUpdate.id;
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = findUserById(id);
        if (!user.enabled) {
            throw new NotFoundException("User with ID '" + id + "' not found");
        } else {
            user.enabled = false;
            user.password = userRepository.findUserPassword(id);
            userRepository.saveAndFlush(user);
        }
    }

    @Override
    public void restoreUser(Long id) {
        User userToRestore = findUserById(id);
        if (userToRestore.enabled) {
            throw new AlreadyExistException(String.format("User with ID %d is already active and doesn't need to be restored", id));
        }
        userToRestore.enabled = true;
        userToRestore.password = userRepository.findUserPassword(id);
        userRepository.save(userToRestore);
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
        userRepository.findByEmail(user.email).ifPresent(e -> {
            throw new AlreadyExistException(String.format("User with email '%s' already exists!", user.email));
        });
        if (user.id != null) {
            userRepository.findById(user.id).ifPresent(e -> {
                throw new AlreadyExistException(String.format("User with ID '%d' already exists!", user.id));
            });
        }
    }

    private void checkAndSetRoles(User user) {
        if (user.roles == null || user.roles.isEmpty()) {
            user.roles = singletonList(roleService.getRoleByName(Roles.USER));
            return;
        }
        List<Role> rolesToSet = map(user.roles, r -> roleService.getRole(r.id));
        user.roles = rolesToSet;
    }

    @Override
    public void createAddress(Long userId, Address address) {
        address.user = getUserById(userId);
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
