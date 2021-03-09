package com.epam.jdi.bookstore.service.impl;

import com.epam.jdi.bookstore.exception.AlreadyExistException;
import com.epam.jdi.bookstore.exception.NotFoundException;
import com.epam.jdi.bookstore.model.Genre;
import com.epam.jdi.bookstore.repository.genre.GenreRepository;
import com.epam.jdi.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre createGenre(Genre genre) throws AlreadyExistException {
        Long genreId = genre.id;
        String genreType = genre.type;
        if (genreId != null) {
            genreRepository.findById(genreId).ifPresent(e -> {
                throw new AlreadyExistException(String.format("Genre with ID '%1$d' already exists. Use 'PUT genres/%1$d' to update existing record", genreId));
            });
        }
        genreRepository.findGenreByType(genreType).ifPresent(e -> {
            throw new AlreadyExistException(String.format("Genre '%s' already exists", genreType));
        });
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Long id) throws NotFoundException {
        return genreRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Genre with ID '%d' not found", id)));
    }

    @Override
    public Genre getGenreByType(String type) throws NotFoundException {
        return genreRepository.findGenreByType(type).orElseThrow(() ->
                new NotFoundException(String.format("Genre with type '%s' not found", type)));
    }

    @Override
    public Genre updateGenre(Long id, Genre genre) throws NotFoundException {
        Genre genreToUpdate = getGenreById(id);
        genre.id = genreToUpdate.id;
        return saveGenre(genre);
    }

    private Genre saveGenre(Genre genre) {
        return genreRepository.saveAndFlush(genre);
    }
}
