package com.essienmichael.cocclient.controllers;

import com.essienmichael.cocclient.models.Client;
import com.essienmichael.cocclient.services.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterController.class)
@AutoConfigureMockMvc(addFilters = false)
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService clientService;


    @Test
    void login() throws Exception {

        when(clientService.registerClient(ArgumentMatchers.any())).thenReturn( new  Client("essienmichael6@gmail.com")
        );

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\": \"Michael\",\n" +
                                "    \"lastName\": \"Essien\", \n" +
                                "    \"email\": \"essienmichael6@gmail.com\", \n" +
                                "    \"password\": \"prototype\", \n" +
                                "    \"dob\": \"2021-04-02\",\n" +
                                "    \"role\": \"User\"\n" +
                                "}")).andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is("essienmichael6@gmail.com")));

    }
}