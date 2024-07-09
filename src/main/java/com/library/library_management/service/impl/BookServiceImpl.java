package com.library.library_management.service.impl;

import com.library.library_management.exception.BookNotFoundException;
import com.library.library_management.model.Author;
import com.library.library_management.model.Book;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.service.BookService;
import com.library.library_management.dto.BookDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;;

@Service
public class BookServiceImpl implements BookService {

    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthorServiceImpl authorService;

    @Override
    public BookDto addBook(BookDto book) {
        Book bookToBeAdded = modelMapper.map(book, Book.class);

        log.info("The book was added.");
        return modelMapper.map(bookRepository.save(bookToBeAdded), BookDto.class);
    }

    @Override
    public BookDto updateBook(String id, BookDto book) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            log.error("The book with the ID {} was not found.", id);
            throw new BookNotFoundException("The book ID does not exist!");
        }

        Book existingBook = optionalBook.get();
        existingBook.setTitle(book.getTitle().isEmpty() ? existingBook.getTitle() : book.getTitle());
        existingBook.setDescription(book.getDescription().isEmpty() ? existingBook.getDescription() : book.getDescription());
        existingBook.setIsbn(StringUtils.isEmpty(book.getIsbn()) ? existingBook.getIsbn() : book.getIsbn());
        existingBook.setPublisher(StringUtils.isEmpty(book.getPublisher()) ? existingBook.getPublisher() : book.getPublisher());
        existingBook.setPublicationDate(book.getPublicationDate() == null ? existingBook.getPublicationDate() : book.getPublicationDate());
        existingBook.setLanguage(StringUtils.isEmpty(book.getLanguage()) ? existingBook.getLanguage() : book.getLanguage());
        existingBook.setNumberOfPages(book.getNumberOfPages() > 0 ? existingBook.getNumberOfPages() : book.getNumberOfPages());
        existingBook.setPrice(book.getPrice() > 0 ? existingBook.getPrice() : book.getPrice());
        existingBook.setAuthor(ObjectUtils.isEmpty(book.getAuthor()) ? existingBook.getAuthor() : modelMapper.map(authorService.updateAuthor(existingBook.getAuthor().getId(), book.getAuthor()), Author.class));

        log.info("The book with the ID {} was updated.", id);
        return modelMapper.map(bookRepository.save(existingBook), BookDto.class);
    }

    @Override
    public void deleteBook(String id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            log.error("The book with the ID {} was not found.", id);
            throw new BookNotFoundException("The book ID does not exist!");
        }

        log.info("The book with the ID {} was deleted.", id);
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> retrieveBooks() {
        List<Book> books = bookRepository.findAll();

        log.info("The list of books was retrieved.");
        return books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public BookDto retrieveBook(String id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            log.error("The book with the ID {} was not found.", id);
            throw new BookNotFoundException("The book ID does not exist!");
        }

        log.info("The book with the ID {} was retrieved.", id);
        return modelMapper.map(book.get(), BookDto.class);
    }
}
