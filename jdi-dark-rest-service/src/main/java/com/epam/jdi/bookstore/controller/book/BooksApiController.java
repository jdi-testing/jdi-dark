package com.epam.jdi.bookstore.controller.book;

import com.epam.jdi.bookstore.controller.BooksApi;
import com.epam.jdi.bookstore.model.Book;
import com.epam.jdi.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class BooksApiController implements BooksApi {

    private BookService booksService;

    @Autowired
    public BooksApiController(BookService booksService) {
        this.booksService = booksService;
    }

    @Override
    public ResponseEntity<Book> createBook(Book book) {
        Book savedBook = booksService.createBook(book);
        URI location = URI.create(String.format("/books/%s", savedBook.id));
        return ResponseEntity.created(location).body(savedBook);
    }

    @Override
    public ResponseEntity<Book> getBookById(Long id) {
        Book book = booksService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<Book> getBookByIsbn(String isbn) {
        Book book = booksService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = booksService.getBooks();
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<List<Book>> getBooksByGenre(Long id) {
        List<Book> books = booksService.getBooksByGenre(id);
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<Void> updateBook(Long id, Book book) {
        booksService.updateBook(id, book);
        return ResponseEntity.ok().build();
    }
}
