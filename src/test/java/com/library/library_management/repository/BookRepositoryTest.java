package com.library.library_management.repository;

import com.library.library_management.model.Author;
import com.library.library_management.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Author author;

    @Test
    public void testSaveBook() {
        String bookId = "123";
        String title = "The Lord of the Rings";
        String description = "An epic high-fantasy trilogy";
        String isbn = "9780261102694";
        String publisher = "Houghton Mifflin Harcourt";
        LocalDate publicationDate = LocalDate.of(1954, 7, 29);
        String language = "English";
        int numberOfPages = 1138;
        double price = 20.99;

        Book book = new Book(bookId, title, description, isbn, publisher, publicationDate, language, numberOfPages, price, author);
        bookRepository.save(book);

        Mockito.verify(bookRepository).save(book);
    }

    @Test
    public void testFindAll() {
        List<Book> allBooks = bookRepository.findAll();
        Mockito.verify(bookRepository).findAll();
    }

    @Test
    public void testFindById() {
        String bookId = "123";

        Book expectedBook = new Book(bookId, "The Lord of the Rings", "...", "...", "...", null, "...", 0, 0.0, author);
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(expectedBook));

        Optional<Book> foundBook = bookRepository.findById(bookId);

        assertTrue(foundBook.isPresent());
        assertEquals(expectedBook, foundBook.get());
    }
}
