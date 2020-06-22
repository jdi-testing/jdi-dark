package com.epam.jdi.bookstore.service.impl;

import com.epam.jdi.bookstore.exception.AlreadyExistException;
import com.epam.jdi.bookstore.exception.InsufficientPermissionsException;
import com.epam.jdi.bookstore.exception.NotFoundException;
import com.epam.jdi.bookstore.repository.user.AddressRepository;
import com.epam.jdi.bookstore.service.RoleService;
import com.epam.jdi.bookstore.utils.constants.Roles;
import com.epam.jdi.bookstore.model.user.Address;
import com.epam.jdi.bookstore.model.user.Role;
import com.epam.jdi.bookstore.model.user.User;
import com.epam.jdi.bookstore.repository.user.UserRepository;
import com.epam.jdi.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.jdi.bookstore.utils.UserUtils.getCurrentUserId;

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
            throw new NotFoundException("User with ID '" + id +"' not found");
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
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException(String.format("User with email '%s' not found!", email)));
        return user;
    }

    private User findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("User with ID '%d' not found!", id)));
        return user;
    }

    private void checkIfUserExists(User user) {
        Optional<User> existingUserById;
        Optional<User> existingUserByName = userRepository.findByEmail(user.getEmail());
        if (existingUserByName.isPresent()) {
            throw new AlreadyExistException(String.format("User with email '%s' already exists!", user.getEmail()));
        }
        if (user.getId() != null) {
            existingUserById = userRepository.findById(user.getId());
            if (existingUserById.isPresent()) {
                throw new AlreadyExistException(String.format("User with ID '%d' already exists!", user.getId()));
            }
        }
    }

    private void checkAndSetRoles(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Collections.singletonList(roleService.getRoleByName(Roles.USER)));
        }
        List<Role> rolesToSet = user.getRoles().stream()
                .map(r -> roleService.getRole(r.getId())).collect(Collectors.toList());
        user.setRoles(rolesToSet);
    }

    @Override
    public void createAddress(Long userId, Address address) {
        if (isHasPermission(userId)) {
            address.setUser(getUserById(userId));
            System.out.println(address);
            addressRepository.save(address);
        } else {
            throw new InsufficientPermissionsException("Insufficient permissions");
        }
    }

    @Override
    public List<Address> getUserAddresses(Long userId) {
        if (isHasPermission(userId)) {
            User user = getUserById(userId);
            return addressRepository.getAddressByUser(user);
        } else {
            throw new InsufficientPermissionsException("Insufficient permissions");
        }
    }

    @Override
    public void updateUserAddress(Long userId, Long addressId, Address address) {
        //TODO Need Implementation
    }

    @Override
    public void deleteUserAddress(Long userId, Long addressId) {
        //TODO Need Implementation
    }

    private boolean isHasPermission(Long userId) {
        User currentUser = getUserById(getCurrentUserId().get());
        return currentUser.getRoles().stream().anyMatch(r -> r.getName().equals(Roles.ADMIN)) || userId.equals(currentUser.getId());
    }
}
