package com.epam.jdi.bookstore.repository.user;

import com.epam.jdi.bookstore.model.user.Role;
import com.epam.jdi.bookstore.repository.BaseRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(String role);
}
