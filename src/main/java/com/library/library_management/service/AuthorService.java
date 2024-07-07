package com.library.library_management.service;

import com.library.library_management.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author addAuthor(Author author);
    Author updateAuthor(String id, Author author);
    void deleteAuthor(String id);
    List<Author> retrieveAuthors();
    Optional<Author> retrieveAuthor(String id);
}
