package com.epam.jdi.bookstore.service.impl;

import com.epam.jdi.bookstore.exception.NotFoundException;
import com.epam.jdi.bookstore.service.RoleService;
import com.epam.jdi.bookstore.model.user.Role;
import com.epam.jdi.bookstore.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleReposiroty) {
        this.roleRepository = roleReposiroty;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) throws NotFoundException {
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (!roleOpt.isPresent()) {
            throw new NotFoundException("Role with ID '" + id + "' not found");
        }
        return roleOpt.get();
    }

    @Override
    public Role getRoleByName(String name) throws NotFoundException {
        Optional<Role> roleOpt = roleRepository.findByName(name);
        if (!roleOpt.isPresent()) {
            throw new NotFoundException("Role with name '" + roleOpt + "' not found");
        }
        return roleOpt.get();
    }
}
