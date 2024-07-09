package com.library.library_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.library_management.dto.AuthorDto;
import com.library.library_management.dto.BookDto;
import com.library.library_management.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private BookDto bookDto;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        bookDto = new BookDto("789", "Clean Code", "A handbook of Agile software craftsmanship",
                "ISBN-1234567890", "Pragmatic Bookshelf", null,
                "English", 431, 39.99,
                new AuthorDto("123", "Michael", "Brown", null, "", "")
        );
    }

    @Test
    public void testDeleteBookById_Success() throws Exception {
        String existingBookId = "123";

        Mockito.doNothing().when(bookService).deleteBook(existingBookId);

        MvcResult mvcResult = mockMvc.perform(delete("/books/" + existingBookId))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals("Deleted Successfully", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testRetrieveBooks_Success() throws Exception {

        List<BookDto> bookList = createMockBooks();
        when(bookService.retrieveBooks()).thenReturn(bookList);

        MvcResult mvcResult = mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        List<BookDto> responseDtoList = objectMapper.readValue(responseBody, List.class);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(bookList.size(), responseDtoList.size());
    }

    @Test
    public void testRetrieveBookById_Success() throws Exception {
        String existingBookId = "789";

        when(bookService.retrieveBook(existingBookId)).thenReturn(bookDto);

        MvcResult mvcResult = mockMvc.perform(get("/books/" + existingBookId))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        BookDto responseDto = objectMapper.readValue(responseBody, BookDto.class);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(bookDto.getId(), responseDto.getId());
        assertEquals(bookDto.getTitle(), responseDto.getTitle());
        assertEquals(bookDto.getDescription(), responseDto.getDescription());
    }

    private List<BookDto> createMockBooks() {
        List<BookDto> books = new ArrayList<>();
        books.add(new BookDto("1", "Clean Code", "...", "...", "...", null, "...", 431, 39.99, null));
        books.add(new BookDto("2", "The Pragmatic Programmer", "...", "...", "...", null, "...", 352, 29.99, null));

        return books;
    }
}