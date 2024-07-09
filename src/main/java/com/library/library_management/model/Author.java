package com.library.library_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * This class represents an Author entity.
 */
@Document(collection = "authors")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String biography;
}
