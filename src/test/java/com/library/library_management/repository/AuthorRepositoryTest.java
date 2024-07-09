package com.library.library_management.repository;


import com.library.library_management.model.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AuthorRepositoryTest {

    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void testSaveAuthor() {
        Author author = new Author("123", "Michael", "Brown", LocalDate.of(1980, 1, 1), "American", "Michael Brown (born January 1, 1980) is a distinguished author known for his captivating novels and insightful non-fiction works. With a passion for storytelling that began in his youth, Michael has become a celebrated figure in the literary world, earning accolades for his compelling characters and engaging narratives. He continues to inspire readers with his unique voice and profound understanding of the human experience.");
        authorRepository.save(author);

        Mockito.verify(authorRepository).save(author);
    }

    @Test
    public void testDeleteById() {
        String authorId = "123";
        authorRepository.deleteById(authorId);

        Mockito.verify(authorRepository).deleteById(authorId);
    }

    @Test
    public void testFindById() {
        String authorId = "123";

        Author expectedAuthor = new Author("123", "Michael", "Brown", LocalDate.of(1980, 1, 1), "American", "Michael Brown (born January 1, 1980) is a distinguished author known for his captivating novels and insightful non-fiction works. With a passion for storytelling that began in his youth, Michael has become a celebrated figure in the literary world, earning accolades for his compelling characters and engaging narratives. He continues to inspire readers with his unique voice and profound understanding of the human experience.");
        Mockito.when(authorRepository.findById(authorId)).thenReturn(Optional.of(expectedAuthor));

        // Call the findById method
        Optional<Author> foundAuthor = authorRepository.findById(authorId);

        // Assertions
        assertTrue(foundAuthor.isPresent());
        assertEquals(expectedAuthor, foundAuthor.get());
    }
}
