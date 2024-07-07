package com.library.library_management.controller;

import com.library.library_management.model.Author;
import com.library.library_management.model.Book;
import com.library.library_management.service.AuthorService;
import com.library.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping()
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,
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
    public ResponseEntity<List<Book>> retrieveBooks() {
        return new ResponseEntity<>(bookService.retrieveBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> retrieveBookById(@PathVariable("id") String bookId) {
        Optional<Book> book = bookService.retrieveBook(bookId);
        return new ResponseEntity<>(book.isPresent() ? book.get() : new Book(), HttpStatus.OK);
    }
}
