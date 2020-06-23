package com.epam.jdi.bookstore.repository.user;

import com.epam.jdi.bookstore.repository.BaseRepository;
import com.epam.jdi.bookstore.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT password FROM user WHERE user.id = ?1", nativeQuery = true)
    String findUserPassword(Long id);
}
