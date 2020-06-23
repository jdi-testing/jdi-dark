package com.epam.jdi.bookstore.repository.genre;

import com.epam.jdi.bookstore.model.Genre;
import com.epam.jdi.bookstore.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends BaseRepository<Genre, Long> {

    Optional<Genre> findGenreByType(String genre);

}
