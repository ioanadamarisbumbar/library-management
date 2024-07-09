package com.library.library_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when an author cannot be found in the system.
 * It extends {@link RuntimeException} and sets the HTTP status code to NOT_FOUND (404)
 * to indicate the resource was not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
