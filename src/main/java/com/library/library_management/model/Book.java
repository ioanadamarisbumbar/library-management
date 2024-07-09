package com.library.library_management.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * This class represents a Book entity.
 */
@Document(collection = "books")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;
    private String title;
    private String description;
    private String isbn;
    private String publisher;
    private LocalDate publicationDate;
    private String language;
    private int numberOfPages;
    private double price;
    private Author author;
}
