package com.epam.jdi.bookstore.repository.user;

import com.epam.jdi.bookstore.model.user.User;
import com.epam.jdi.bookstore.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u.password FROM User u WHERE u.id = ?1")
    String findUserPassword(Long id);
}
