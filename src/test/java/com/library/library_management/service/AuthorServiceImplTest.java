package com.library.library_management.service;

import com.library.library_management.exception.AuthorNotFoundException;
import com.library.library_management.model.Author;
import com.library.library_management.repository.AuthorRepository;
import com.library.library_management.service.impl.AuthorServiceImpl;
import com.library.library_management.dto.AuthorDto;
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
public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private ModelMapper modelMapper;

    private AuthorDto authorDto;
    private Author author;
    private String authorId = "123";

    @BeforeEach
    public void setUp() {
        authorDto = new AuthorDto("123", "Michael", "Brown", LocalDate.of(1980, 1, 1), "American", "Michael Brown (born January 1, 1980) is a distinguished author known for his captivating novels and insightful non-fiction works. With a passion for storytelling that began in his youth, Michael has become a celebrated figure in the literary world, earning accolades for his compelling characters and engaging narratives. He continues to inspire readers with his unique voice and profound understanding of the human experience.");
        author = new Author("123", "Michael", "Brown", LocalDate.of(1980, 1, 1), "American", "Michael Brown (born January 1, 1980) is a distinguished author known for his captivating novels and insightful non-fiction works. With a passion for storytelling that began in his youth, Michael has become a celebrated figure in the literary world, earning accolades for his compelling characters and engaging narratives. He continues to inspire readers with his unique voice and profound understanding of the human experience.");
    }

    @Test
    public void testAddAuthor() {
        Mockito.when(modelMapper.map(authorDto, Author.class)).thenReturn(author);
        Mockito.when(modelMapper.map(author, AuthorDto.class)).thenReturn(authorDto);
        Mockito.when(authorRepository.save(author)).thenReturn(author);

        AuthorDto savedAuthorDto = authorService.addAuthor(authorDto);

        assertEquals(authorDto, savedAuthorDto);
        Mockito.verify(authorRepository).save(author);
    }

    @Test
    public void testUpdateAuthor_NotExistingAuthor() {
        Optional<Author> optionalAuthor = Optional.empty();
        Mockito.when(authorRepository.findById(authorId)).thenReturn(optionalAuthor);

        try {
            authorService.updateAuthor(authorId, authorDto);
            fail("Expected AuthorNotFoundException to be thrown!");
        } catch (AuthorNotFoundException e) {
            assertEquals("The author ID does not exist!", e.getMessage());
        }

        Mockito.verify(authorRepository).findById(authorId);
        Mockito.verify(authorRepository, Mockito.never()).save(author);
    }

    @Test
    public void testDeleteAuthor_ExistingAuthor() {
        Optional<Author> optionalAuthor = Optional.of(author);
        Mockito.when(authorRepository.findById(authorId)).thenReturn(optionalAuthor);
        authorService.deleteAuthor(authorId);

        Mockito.verify(authorRepository).findById(authorId);
        Mockito.verify(authorRepository).deleteById(authorId);
    }

    @Test
    public void testDeleteAuthor_NotExistingAuthor() {
        Optional<Author> optionalAuthor = Optional.empty();
        Mockito.when(authorRepository.findById(authorId)).thenReturn(optionalAuthor);

        try {
            authorService.deleteAuthor(authorId);
            fail("Expected AuthorNotFoundException to be thrown!");
        } catch (AuthorNotFoundException e) {
            assertEquals("The author ID does not exist!", e.getMessage());
        }

        Mockito.verify(authorRepository).findById(authorId);
        Mockito.verify(authorRepository, Mockito.never()).deleteById(authorId);
    }

    @Test
    public void testRetrieveAuthors_Success() {
        List<Author> authors = List.of(
                new Author("1", "John", "Doe", null, "", ""),
                new Author("2", "Jane", "Smith", null, "", "")
        );
        Mockito.when(authorRepository.findAll()).thenReturn(authors);
        Mockito.when(modelMapper.map(authors.get(0), AuthorDto.class)).thenReturn(
                new AuthorDto("1", "John", "Doe", null, "", "")
        );
        Mockito.when(modelMapper.map(authors.get(1), AuthorDto.class)).thenReturn(
                new AuthorDto("2", "Jane", "Smith", null, "", "")
        );

        List<AuthorDto> retrievedAuthors = authorService.retrieveAuthors();

        assertEquals(2, retrievedAuthors.size());
        for (int i = 0; i < retrievedAuthors.size(); i++) {
            AuthorDto retrievedDto = retrievedAuthors.get(i);
            Author expectedAuthor = authors.get(i);
            assertEquals(expectedAuthor.getId(), retrievedDto.getId());
            assertEquals(expectedAuthor.getFirstName(), retrievedDto.getFirstName());
            assertEquals(expectedAuthor.getLastName(), retrievedDto.getLastName());
        }

        Mockito.verify(authorRepository).findAll();
    }

    @Test
    public void testRetrieveAuthor_Success() {
        Optional<Author> optionalAuthor = Optional.of(author);
        Mockito.when(authorRepository.findById(authorId)).thenReturn(optionalAuthor);
        Mockito.when(modelMapper.map(author, AuthorDto.class)).thenReturn(authorDto);

        AuthorDto retrievedAuthor = authorService.retrieveAuthor(authorId);

        assertNotNull(retrievedAuthor, "Retrieved author should not be null");
        assertEquals(authorId, retrievedAuthor.getId());
        assertEquals("Michael", retrievedAuthor.getFirstName());
        assertEquals("Brown", retrievedAuthor.getLastName());
        Mockito.verify(authorRepository).findById(authorId);
    }

    @Test
    public void testRetrieveAuthor_AuthorNotFound() {
        Optional<Author> optionalAuthor = Optional.empty();
        Mockito.when(authorRepository.findById(authorId)).thenReturn(optionalAuthor);

        try {
            authorService.retrieveAuthor(authorId);
            fail("Expected AuthorNotFoundException to be thrown!");
        } catch (AuthorNotFoundException e) {
            assertEquals("The author ID does not exist!", e.getMessage());
        }

        Mockito.verify(authorRepository).findById(authorId);
    }
}
