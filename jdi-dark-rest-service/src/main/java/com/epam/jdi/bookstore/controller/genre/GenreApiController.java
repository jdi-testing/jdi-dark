package com.epam.jdi.bookstore.controller.genre;

import com.epam.jdi.bookstore.controller.GenreApi;
import com.epam.jdi.bookstore.model.Genre;
import com.epam.jdi.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class GenreApiController implements GenreApi {

    private GenreService genreService;

    @Autowired
    public GenreApiController(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public ResponseEntity<Genre> createGenre(@Valid Genre genre) {
        Genre savedGenre = genreService.createGenre(genre);
        URI location = URI.create(String.format("/genres/%s", savedGenre.getId()));
        return ResponseEntity.created(location).body(savedGenre);
    }

    @Override
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) {
        Genre genre = genreService.getGenreById(id);
        return ResponseEntity.ok(genre);
    }

    @Override
    public ResponseEntity<List<Genre>> getGenres() {
        List<Genre> genres = genreService.getGenres();
        return ResponseEntity.ok(genres);
    }

    @Override
    public ResponseEntity<Genre> updateGenre(Long id, Genre genre) {
        Genre updatedGenre = genreService.updateGenre(id, genre);
        return ResponseEntity.ok(updatedGenre);
    }
}
