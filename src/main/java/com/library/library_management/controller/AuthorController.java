package com.library.library_management.controller;

import com.library.library_management.exception.AuthorNotFoundException;
import com.library.library_management.model.Author;
import com.library.library_management.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping()
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author,
                                               @PathVariable("id") String authorId) {

//        Author newAuthor = null;
//        try {
//            newAuthor =  authorService.updateAuthor(
//                    authorId, author);
//        return new ResponseEntity<>(newAuthor, HttpStatus.OK);
        return new ResponseEntity<>(authorService.updateAuthor(
                authorId, author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable("id") String authorId) {
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Author>> retrieveAuthors() {
        return new ResponseEntity<>(authorService.retrieveAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> retrieveAuthorById(@PathVariable("id") String authorId) {
        Optional<Author> author = authorService.retrieveAuthor(authorId);
        return new ResponseEntity<>(author.isPresent() ? author.get() : new Author(), HttpStatus.OK);
    }
}
