package com.epam.jdi.bookstore.service.impl;

import com.epam.jdi.bookstore.model.Book;
import com.epam.jdi.bookstore.exception.AlreadyExistException;
import com.epam.jdi.bookstore.exception.NotFoundException;
import com.epam.jdi.bookstore.model.Genre;
import com.epam.jdi.bookstore.repository.book.BookRepository;
import com.epam.jdi.bookstore.service.BookService;
import com.epam.jdi.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        bookRepository.findBookByIsbn(book.getIsbn()).ifPresent(e -> {
            throw new AlreadyExistException("Book with ISBN '" + e.getIsbn() + "' already exists");
        });
        return saveBook(book);
    }

    @Override
    public Book getBook(Long id) throws NotFoundException {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (!bookOpt.isPresent()) {
            throw new NotFoundException("Book with ID '" + id + "' not found");
        }
        return bookOpt.get();
    }

    @Override
    public Book getBookByIsbn(String isbn) throws NotFoundException {
        Optional<Book> bookOpt = bookRepository.findBookByIsbn(isbn);
        if (!bookOpt.isPresent()) {
            throw new NotFoundException("Book with ISBN '" + isbn + "' not found");
        }
        return bookOpt.get();
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
        book.setId(bookToUpdate.getId());
        findAndSetGenres(book);
        saveBook(book);
    }

    private Book saveBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    private void findAndSetGenres(Book book) {
        List<Genre> genres = book.getGenres();
        genres.forEach(g -> g.setId(genreService.getGenreByType(g.getType()).getId()));
        book.setGenres(genres);
    }
}
