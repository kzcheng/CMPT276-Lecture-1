package com.example.demo.controllers;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.models.UserRepository;
import com.example.demo.models.Users;
import static org.mockito.Mockito.when;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UsersController.class)
public class UsersControllerTest {
    
    @MockBean
    private UserRepository userRepository;
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllUsers() throws Exception {

        Users u1 = new Users();
        u1.setName("bobby");
        u1.setPassword("1234");
        u1.setAge(42);

        Users u2 = new Users();
        u2.setName("jane");
        u2.setPassword("1234");
        u2.setAge(25);

        List<Users> users = new ArrayList<Users>();
        users.add(u1);
        users.add(u2);

        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("users/showAll"))
            
            .andExpect(MockMvcResultMatchers.model().attribute("usrs", hasItem(
                allOf(
                    hasProperty("name", Matchers.is("bobby")),
                    hasProperty("password", Matchers.is("1234")),
                    hasProperty("age", Matchers.is(42))
                )
            )));
    }
    
    // @Test
    // void testGetLogin() throws Exception {
    //     mockMvc.perform(MockMvcRequestBuilders.get("/login"))
    //         .andExpect(MockMvcResultMatchers.status().isOk())
    //         .andExpect(MockMvcResultMatchers.view().name("users/login"));
    // }

}
