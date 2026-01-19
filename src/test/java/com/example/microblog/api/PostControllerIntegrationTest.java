package com.example.microblog.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void listPosts_endpointReturnsJsonList() throws Exception {
        mockMvc.perform(get("/posts/alice"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content", hasSize(greaterThan(0))))
               .andExpect(jsonPath("$.content[0].content", notNullValue()));
    }
}
