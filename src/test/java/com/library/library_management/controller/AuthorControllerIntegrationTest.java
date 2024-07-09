package com.library.library_management.controller;


import com.library.library_management.dto.AuthorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddAuthor() {
        AuthorDto authorDto = new AuthorDto("123", "Michael", "Brown", null, "American", "");
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity("http://localhost:" + port + "/authors", authorDto, String.class);
        assertEquals(201, responseEntity.getStatusCode().value());
    }
}
