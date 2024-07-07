package com.library.library_management.service.impl;

import com.library.library_management.exception.BookNotFoundException;
import com.library.library_management.model.Author;
import com.library.library_management.model.Book;
//import com.library.library_management.repository.BookRepository;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    AuthorServiceImpl authorService;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
//        return new Book(11l, "ti", "de", "isbn", new Author(11l, "a", "b"));
    }

    @Override
    public Book updateBook(String id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new BookNotFoundException("The book ID does not exist!");
        }

        Book savedBook = optionalBook.get();
        savedBook.setTitle(book.getTitle().isEmpty() ? savedBook.getTitle() : book.getTitle());
        savedBook.setDescription(book.getDescription().isEmpty() ? savedBook.getDescription() : book.getDescription());
        savedBook.setIsbn(book.getIsbn().isEmpty() ? savedBook.getIsbn() : book.getIsbn());
        savedBook.setPublisher(book.getPublisher().isEmpty() ? savedBook.getPublisher() : book.getPublisher());
        savedBook.setLanguage(book.getLanguage().isEmpty() ? savedBook.getLanguage() : book.getLanguage());
        savedBook.setNumberOfPages(book.getNumberOfPages() > 0 ? savedBook.getNumberOfPages() : book.getNumberOfPages());
        savedBook.setPrice(book.getPrice() > 0 ? savedBook.getPrice() : book.getPrice());
        savedBook.setAuthor(ObjectUtils.isEmpty(book.getAuthor()) ? savedBook.getAuthor() : authorService.updateAuthor(savedBook.getAuthor().getId(), savedBook.getAuthor()));

        return bookRepository.save(savedBook);

//        return new Book(11l, "ti", "de", "isbn", new Author(11l, "a", "b"));
    }

    @Override
    public void deleteBook(String id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new BookNotFoundException("The book ID does not exist!");
        }

        bookRepository.deleteById(id);
//        System.out.println("delete book");
    }

    @Override
    public List<Book> retrieveBooks() {
        return bookRepository.findAll();
//        return new ArrayList<>(Arrays.asList(new Book(11l, "ti", "de", "isbn", new Author(11l, "a", "b")), new Book(177l, "title", "description", "isbn", new Author(15l, "ab", "ba"))));
    }

    @Override
    public Optional<Book> retrieveBook(String id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new BookNotFoundException("The book ID does not exist!");
        }
        return book;
//        return Optional.of(new Book(11l, "ti", "de", "isbn", new Author(11l, "a", "b")));
    }

//    @Override
//    public Book retrieveBook(Long id) {
//        Optional<Book> book = bookRepository.findById(id);
//        return book.isPresent() ? book.get() : new Book();
//    }
}
