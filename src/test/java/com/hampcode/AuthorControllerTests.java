package com.hampcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    void createAuthorEndpointReturnsCreatedAuthor() throws Exception {
        String body = "{\"name\":\"Laura Restrepo\"}";
        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Laura Restrepo"));
    }

    @Test
    void searchAuthorsByNameReturnsList() throws Exception {
        mockMvc.perform(get("/api/v1/authors/search")
                .param("name", "Gabriel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gabriel García Márquez"));
    }
}
