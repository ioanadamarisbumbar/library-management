package com.library.library_management.controller;

import com.library.library_management.dto.AuthorDto;
import com.library.library_management.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a Spring MVC REST controller for managing authors in a database.
 * It maps incoming HTTP requests to corresponding methods for CRUD operations on authors
 * and delegates the logic to the injected AuthorService.
 */
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping()
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@RequestBody AuthorDto author,
                                                  @PathVariable("id") String authorId) {
        return new ResponseEntity<>(authorService.updateAuthor(
                authorId, author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable("id") String authorId) {
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> retrieveAuthors() {
        return new ResponseEntity<>(authorService.retrieveAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> retrieveAuthorById(@PathVariable("id") String authorId) {
        return new ResponseEntity<>(authorService.retrieveAuthor(authorId), HttpStatus.OK);
    }
}
