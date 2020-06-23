package com.epam.jdi.bookstore.service.impl;

import com.epam.jdi.bookstore.exception.AlreadyExistException;
import com.epam.jdi.bookstore.exception.NotFoundException;
import com.epam.jdi.bookstore.model.Genre;
import com.epam.jdi.bookstore.service.GenreService;
import com.epam.jdi.bookstore.repository.genre.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre createGenre(Genre genre) throws AlreadyExistException {
        if (genre.getId() != null) {
            Optional<Genre> existingGenre = genreRepository.findById(genre.getId());
            if (existingGenre.isPresent()) {
                throw new AlreadyExistException("Genre with ID '" + genre.getId() + "' already exists. Use 'PUT genres/" + genre.getId() + "' to update existing record");
            }
        }
        genreRepository.findGenreByType(genre.getType()).ifPresent(e -> {
            throw new AlreadyExistException("Genre '" + e.getType() + "' already exists");
        });
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Long id) throws NotFoundException {
        Optional<Genre> genre = genreRepository.findById(id);
        if (!genre.isPresent()) {
            throw new NotFoundException("Genre with ID '" + id + "' not found");
        }
        return genre.get();
    }

    @Override
    public Genre updateGenre(Long id, Genre genre) throws NotFoundException {
        Genre genreToUpdate = getGenreById(id);
        genre.setId(genreToUpdate.getId());
        return saveGenre(genre);
    }

    private Genre saveGenre(Genre genre) {
        return genreRepository.saveAndFlush(genre);
    }
}
