package com.example.web;


import com.example.model.dto.UserBindingModel;
import com.example.model.dto.UserRegistrationDTO;
import com.example.model.entity.UserEntity;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;
    private String baseUrl = "http://localhost";


   @Test
    public void testRegisterFormComes() throws Exception {

       mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("auth-register"));


   }


    @Test
    public void testLoginFormComes() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("auth-login"));


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAllUsersCome() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void testSellerDetailsCome() throws Exception {

        UserBindingModel user = new UserBindingModel();
        user.setFirstName("gosho");
        user.setLastName("petrov");
       when(userService.getById(1L)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/seller-details/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testSellerDetailSuccessfully() throws Exception {

        UserBindingModel user = new UserBindingModel();
        user.setFirstName("gosho");
        user.setLastName("petrov");
        when(userService.getById(1L)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/seller-details/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testBanUserSuccessfully() throws Exception {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFirstName("gosho");
        user.setLastName("petrov");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/ban-user/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/users/all"));





    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testUnBanUserSuccessfully() throws Exception {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFirstName("gosho");
        user.setLastName("petrov");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/unban-user/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/users/all"));





    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testMakeUserVipSuccessfully() throws Exception {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFirstName("gosho");
        user.setLastName("petrov");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/make-vip/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/users/all"));





    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testMakeUserUserSuccessfully() throws Exception {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFirstName("gosho");
        user.setLastName("petrov");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/make-user/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/users/all"));





    }











    @Test
    void testUserRegistrationSuccessfully() throws Exception {

        when(userService.checkForUniqueEmail("pesho@example.com")).thenReturn(true);
        mockMvc.perform(post(baseUrl + "/users/register").
                        param("email", "pesho@example.com").
                        param("username", "pesho").
                        param("firstName", "Petur").
                        param("lastName", "Petrov").
                        param("password", "1234").
                        param("confirmPassword", "1234")
                        .param("profilePictureUrl", "https://test")
                        .with(csrf())

                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection()).
                andExpect(redirectedUrl(  "/users/login"));
        ;
    }

    @Test
    void testUserRegistrationUnsuccessfullyNotUniqueEmail() throws Exception {

        when(userService.checkForUniqueEmail("pesho@example.com")).thenReturn(false);
        mockMvc.perform(post(baseUrl + "/users/register").
                        param("email", "pesho@example.com").
                        param("username", "pesho").
                        param("firstName", "Petur").
                        param("lastName", "Petrov").
                        param("password", "1234").
                        param("confirmPassword", "1234")
                        .param("profilePictureUrl", "https://test")
                        .with(csrf())

                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection()).
                andExpect(redirectedUrl(  "/users/register"));

    }


    @Test
    void testUserRegistrationUnsuccessfullyConfirmPasswordIsnNotTheSame() throws Exception {

        when(userService.checkForUniqueEmail("pesho@example.com")).thenReturn(true);
        mockMvc.perform(post(baseUrl + "/users/register").
                        param("email", "pesho@example.com").
                        param("username", "pesho").
                        param("firstName", "Petur").
                        param("lastName", "Petrov").
                        param("password", "1234").
                        param("confirmPassword", "12345")
                        .param("profilePictureUrl", "https://test")
                        .with(csrf())

                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection()).
                andExpect(redirectedUrl(  "/users/register"));

    }




}