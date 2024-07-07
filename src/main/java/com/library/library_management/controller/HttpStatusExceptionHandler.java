package com.library.library_management.controller;

import com.library.library_management.exception.AuthorNotFoundException;
import com.library.library_management.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HttpStatusExceptionHandler {

    @ExceptionHandler(value = BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e) {
        return new ResponseEntity<>("Book not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException e) {
        return new ResponseEntity<>("Author not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}