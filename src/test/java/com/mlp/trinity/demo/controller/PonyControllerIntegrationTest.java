package com.mlp.trinity.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
@SpringBootTest
@AutoConfigureMockMvc
public class PonyControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PonyController mockPonyController;

    @Test
    public void getName_ReturnName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pony"))
                .andReturn()
                .getResponse();

        verify(mockPonyController).getAllPonys();
    }
}
