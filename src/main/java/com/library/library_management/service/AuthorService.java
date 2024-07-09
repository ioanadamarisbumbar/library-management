package com.library.library_management.service;

import com.library.library_management.dto.AuthorDto;

import java.util.List;

/**
 * Interface defining operations for the Author service.
 * This service provides methods for managing author data in the database.
 */
public interface AuthorService {

    /**
     * Adds a new author to the database.
     *
     * @param author The AuthorDto object providing data for the new author.
     * @return A new AuthorDto object representing the added author with its generated ID.
     */
    AuthorDto addAuthor(AuthorDto author);

    /**
     * Updates an existing author in the database.
     *
     * @param id     The ID of the author to be updated.
     * @param author The AuthorDto object containing updated data for the author.
     * @return A new AuthorDto object representing the updated author.
     */
    AuthorDto updateAuthor(String id, AuthorDto author);

    /**
     * Deletes an author from the database.
     *
     * @param id The ID of the author to be deleted.
     */
    void deleteAuthor(String id);

    /**
     * Retrieves a list of all authors in the database.
     *
     * @return A List of AuthorDto objects representing all authors.
     */
    List<AuthorDto> retrieveAuthors();

    /**
     * Retrieves a specific author by their ID.
     *
     * @param id The ID of the author to be retrieved.
     * @return An AuthorDto object representing the requested author, or null if not found.
     */
    AuthorDto retrieveAuthor(String id);
}