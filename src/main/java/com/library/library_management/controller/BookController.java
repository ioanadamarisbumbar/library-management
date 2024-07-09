package com.library.library_management.controller;

import com.library.library_management.dto.BookDto;
import com.library.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a Spring MVC REST controller for managing books in a database.
 * It maps incoming HTTP requests to corresponding methods for CRUD operations on books
 * and delegates the logic to the injected BookService.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping()
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto book,
                                              @PathVariable("id") String bookId) {
        return new ResponseEntity<>(bookService.updateBook(
                bookId, book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") String bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> retrieveBooks() {
        return new ResponseEntity<>(bookService.retrieveBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> retrieveBookById(@PathVariable("id") String bookId) {
        return new ResponseEntity<>(bookService.retrieveBook(bookId), HttpStatus.OK);
    }
}
