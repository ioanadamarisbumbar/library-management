package com.library.library_management.service;

import com.library.library_management.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book addBook(Book book);
    Book updateBook(String id, Book book);
    void deleteBook(String id);
    List<Book> retrieveBooks();
    Optional<Book> retrieveBook(String id);
}
