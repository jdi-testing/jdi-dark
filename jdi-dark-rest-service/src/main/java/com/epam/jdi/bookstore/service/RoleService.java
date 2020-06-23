package com.epam.jdi.bookstore.service;

import com.epam.jdi.bookstore.model.user.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    Role getRole(Long id);

    Role getRoleByName(String name);

}
