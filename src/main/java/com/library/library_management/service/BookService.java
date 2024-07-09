package com.library.library_management.service;

import com.library.library_management.dto.BookDto;

import java.util.List;

/**
 * Interface defining operations for the Book service.
 * This service provides methods for managing book data in the database.
 */
public interface BookService {

    /**
     * Adds a new book to the database.
     *
     * @param book The BookDto object providing data for the new book.
     * @return A BookDto object representing the newly added book with its generated ID.
     */
    BookDto addBook(BookDto book);

    /**
     * Updates an existing book in the database.
     *
     * @param id   The ID of the book to be updated.
     * @param book The BookDto object containing updated data for the book.
     * @return A BookDto object representing the updated book.
     */
    BookDto updateBook(String id, BookDto book);

    /**
     * Deletes a book from the database.
     *
     * @param id The ID of the book to be deleted.
     */
    void deleteBook(String id);

    /**
     * Retrieves a list of all books in the database.
     *
     * @return A List of BookDto objects representing all books.
     */
    List<BookDto> retrieveBooks();

    /**
     * Retrieves a specific book by its ID.
     *
     * @param id The ID of the book to be retrieved.
     * @return A BookDto object representing the requested book, or null if not found.
     */
    BookDto retrieveBook(String id);
}
