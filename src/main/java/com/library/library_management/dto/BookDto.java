package com.library.library_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String id;
    private String title;
    private String description;
    private String isbn;
    private String publisher;
    private LocalDate publicationDate;
    private String language;
    private int numberOfPages;
    private double price;
    private AuthorDto author;
}
