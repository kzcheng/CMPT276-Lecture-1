package com.example.demo.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PuppyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PuppyController puppyController;

    @BeforeAll
    static void setup() {
        System.out.println("Setting up");
    }

    @Test
    void testContextLoads() throws Exception {
        assertNotNull(puppyController);
    }

    @Test
    void testGetAllPuppies() throws Exception {
        this.mockMvc.perform(get("/puppies"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].name", is("Buddy")))
            .andExpect(jsonPath("$[0].breed", is("Golden Retriever")))
            .andExpect(jsonPath("$[0].color", is("Golden")))
            .andExpect(jsonPath("$[1].name", is("Max")))
            .andExpect(jsonPath("$[1].breed", is("German Shepherd")))
            .andExpect(jsonPath("$[1].color", is("Black/Brown")))
            .andExpect(jsonPath("$[2].name", is("Charlie")))
            .andExpect(jsonPath("$[2].breed", is("Bulldog")))
            .andExpect(jsonPath("$[2].color", is("White")));
    }

}
