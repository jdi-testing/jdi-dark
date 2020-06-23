package com.epam.jdi.bookstore.service;

import com.epam.jdi.bookstore.exception.AlreadyExistException;
import com.epam.jdi.bookstore.exception.NotFoundException;
import com.epam.jdi.bookstore.model.Genre;

import java.util.List;

public interface GenreService {

    Genre createGenre(Genre genre) throws AlreadyExistException;

    List<Genre> getGenres();

    Genre getGenreById(Long id) throws NotFoundException;

    Genre updateGenre(Long id, Genre genre) throws NotFoundException;

}
