package com.library.library_management.service;

import com.library.library_management.exception.BookNotFoundException;
import com.library.library_management.model.Author;
import com.library.library_management.model.Book;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.service.impl.BookServiceImpl;
import com.library.library_management.dto.AuthorDto;
import com.library.library_management.dto.BookDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    private BookDto bookDto;
    private Book book;
    private String bookId = "789";

    @BeforeEach
    public void setUp() {
        bookDto = new BookDto("789", "Clean Code", "A handbook of Agile software craftsmanship",
                "ISBN-1234567890", "Pragmatic Bookshelf", LocalDate.of(2008, 8, 1),
                "English", 431, 39.99,
                    new AuthorDto("123", "Michael", "Brown", LocalDate.of(1980, 1, 1), "American", "")
                );

        book = new Book("789", "Clean Code", "A handbook of Agile software craftsmanship",
                "ISBN-1234567890", "Pragmatic Bookshelf", LocalDate.of(2008, 8, 1),
                "English", 431, 39.99,
                        new Author("123", "Michael", "Brown", LocalDate.of(1980, 1, 1), "American", "")
                );
    }

    @Test
    public void testAddBook() {
        Mockito.when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
        Mockito.when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
        Mockito.when(bookRepository.save(book)).thenReturn(book);

        BookDto savedBookDto = bookService.addBook(bookDto);

        assertEquals(bookDto, savedBookDto);
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    public void testUpdateBook_NotExistingBook() {
        Optional<Book> optionalBook = Optional.empty();
        Mockito.when(bookRepository.findById(bookId)).thenReturn(optionalBook);

        try {
            bookService.updateBook(bookId, bookDto);
            fail("Expected BookNotFoundException to be thrown!");
        } catch (BookNotFoundException e) {
            assertEquals("The book ID does not exist!", e.getMessage());
        }

        Mockito.verify(bookRepository).findById(bookId);
        Mockito.verify(bookRepository, Mockito.never()).save(book);
    }

    @Test
    public void testDeleteBook_ExistingBook() {
        Optional<Book> optionalBook = Optional.of(book);
        Mockito.when(bookRepository.findById(bookId)).thenReturn(optionalBook);
        bookService.deleteBook(bookId);

        Mockito.verify(bookRepository).findById(bookId);
        Mockito.verify(bookRepository).deleteById(bookId);
    }

    @Test
    public void testDeleteBook_NotExistingBook() {
        Optional<Book> optionalBook = Optional.empty();
        Mockito.when(bookRepository.findById(bookId)).thenReturn(optionalBook);

        try {
            bookService.deleteBook(bookId);
            fail("Expected BookNotFoundException to be thrown!");
        } catch (BookNotFoundException e) {
            assertEquals("The book ID does not exist!", e.getMessage());
        }

        Mockito.verify(bookRepository).findById(bookId);
        Mockito.verify(bookRepository, Mockito.never()).deleteById(bookId);
    }

    @Test
    public void testRetrieveBooks_Success() {
        List<Book> books = List.of(
                new Book("1", "Clean Code", "...", "...", "...", LocalDate.now(), "...", 431, 39.99, null),
                new Book("2", "The Pragmatic Programmer", "...", "...", "...", LocalDate.now(), "...", 352, 29.99, null)
        );
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        Mockito.when(modelMapper.map(books.get(0), BookDto.class)).thenReturn(
                new BookDto("1", "Clean Code", "...", "...", "...", LocalDate.now(), "...", 431, 39.99, null)
        );
        Mockito.when(modelMapper.map(books.get(1), BookDto.class)).thenReturn(
                new BookDto("2", "The Pragmatic Programmer", "...", "...", "...", LocalDate.now(), "...", 352, 29.99, null)
        );

        List<BookDto> retrievedBooks = bookService.retrieveBooks();

        assertEquals(2, retrievedBooks.size());
        for (int i = 0; i < retrievedBooks.size(); i++) {
            BookDto retrievedDto = retrievedBooks.get(i);
            Book expectedBook = books.get(i);
            assertEquals(expectedBook.getId(), retrievedDto.getId());
            assertEquals(expectedBook.getTitle(), retrievedDto.getTitle());
            assertEquals(expectedBook.getDescription(), retrievedDto.getDescription());
        }

        Mockito.verify(bookRepository).findAll();
    }

    @Test
    public void testRetrieveBook_Success() {
        Optional<Book> optionalBook = Optional.of(book);
        Mockito.when(bookRepository.findById(bookId)).thenReturn(optionalBook);
        Mockito.when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);

        BookDto retrievedBook = bookService.retrieveBook(bookId);

        assertNotNull(retrievedBook, "Retrieved book should not be null");
        assertEquals(bookId, retrievedBook.getId());
        assertEquals("Clean Code", retrievedBook.getTitle());
        assertEquals("A handbook of Agile software craftsmanship", retrievedBook.getDescription());
        Mockito.verify(bookRepository).findById(bookId);
    }

    @Test
    public void testRetrieveBook_BookNotFound() {
        Optional<Book> optionalBook = Optional.empty();
        Mockito.when(bookRepository.findById(bookId)).thenReturn(optionalBook);

        try {
            bookService.retrieveBook(bookId);
            fail("Expected BookNotFoundException to be thrown!");
        } catch (BookNotFoundException e) {
            assertEquals("The book ID does not exist!", e.getMessage());
        }

        Mockito.verify(bookRepository).findById(bookId);
    }
}
