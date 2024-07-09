package com.library.library_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.library_management.dto.AuthorDto;
import com.library.library_management.service.AuthorService;
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
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    private AuthorDto authorDto;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        authorDto = new AuthorDto("123", "Michael", "Brown", null, "American", "");
    }

    @Test
    public void testDeleteAuthorById_Success() throws Exception {
        String existingAuthorId = "123";

        Mockito.doNothing().when(authorService).deleteAuthor(existingAuthorId);

        MvcResult mvcResult = mockMvc.perform(delete("/authors/" + existingAuthorId))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals("Deleted Successfully", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testRetrieveAuthors_Success() throws Exception {

        List<AuthorDto> authorList = createMockAuthors();
        when(authorService.retrieveAuthors()).thenReturn(authorList);

        MvcResult mvcResult = mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        List<AuthorDto> responseDtoList = objectMapper.readValue(responseBody, List.class);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(authorList.size(), responseDtoList.size());
    }

    @Test
    public void testRetrieveAuthorById_Success() throws Exception {
        String existingAuthorId = "123";

        when(authorService.retrieveAuthor(existingAuthorId)).thenReturn(authorDto);

        MvcResult mvcResult = mockMvc.perform(get("/authors/" + existingAuthorId))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        AuthorDto responseDto = objectMapper.readValue(responseBody, AuthorDto.class);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(authorDto.getId(), responseDto.getId());
        assertEquals(authorDto.getFirstName(), responseDto.getFirstName());
        assertEquals(authorDto.getLastName(), responseDto.getLastName());
    }

    private List<AuthorDto> createMockAuthors() {
        List<AuthorDto> authors = new ArrayList<>();
        authors.add(new AuthorDto("123", "John", "Doe", null, null, null));
        authors.add(new AuthorDto("456", "Jane", "Doe", null, null, null));
        return authors;
    }
}