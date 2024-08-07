package com.library.library_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * This class represents a Data Transfer Object (DTO) for Author entities.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String biography;
}
