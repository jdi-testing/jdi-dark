package com.epam.jdi.bookstore.service.impl;

import com.epam.jdi.bookstore.exception.AlreadyExistException;
import com.epam.jdi.bookstore.exception.NotFoundException;
import com.epam.jdi.bookstore.model.Book;
import com.epam.jdi.bookstore.model.Genre;
import com.epam.jdi.bookstore.repository.book.BookRepository;
import com.epam.jdi.bookstore.service.BookService;
import com.epam.jdi.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;

    @Autowired
    public BooksServiceImpl(BookRepository bookRepository, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
    }

    @Override
    public Book createBook(Book book) {
        findAndSetGenres(book);
        bookRepository.findBookByIsbn(book.isbn).ifPresent(e -> {
            throw new AlreadyExistException(String.format("Book with ISBN '%s' already exists", book.isbn));
        });
        return saveBook(book);
    }

    @Override
    public Book getBook(Long id) throws NotFoundException {
        return bookRepository.findById(id).orElseThrow(() ->
            new NotFoundException(String.format("Book with ID '%d' not found", id)));
    }

    @Override
    public Book getBookByIsbn(String isbn) throws NotFoundException {
        return  bookRepository.findBookByIsbn(isbn).orElseThrow(() ->
            new AlreadyExistException(String.format("Book with ISBN '%s' already exists", isbn)));
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByGenre(Long id) {
        Genre genre = genreService.getGenreById(id);
        return bookRepository.findBooksByGenres(genre);
    }

    @Override
    public void updateBook(Long id, Book book) {
        Book bookToUpdate = getBook(id);
        book.id = bookToUpdate.id;
        findAndSetGenres(book);
        saveBook(book);
    }

    private Book saveBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    private void findAndSetGenres(Book book) {
        List<Genre> genres = book.genres;
        genres.forEach(g -> g.id = genreService.getGenreByType(g.type).id);
        book.genres = genres;
    }
}
