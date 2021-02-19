package com.epam.jdi.bookstore.repository.book;

import com.epam.jdi.bookstore.model.Book;
import com.epam.jdi.bookstore.model.Genre;
import com.epam.jdi.bookstore.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book, Long> {

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findBooksByGenres(Genre genre);
}
