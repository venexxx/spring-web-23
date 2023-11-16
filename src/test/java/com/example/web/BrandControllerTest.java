package com.example.web;

import com.example.model.dto.AddBrandDTO;
import com.example.model.dto.AddModelDTO;
import com.example.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService mockService;


    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAllBrandsFormComes() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/brands"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("brands"));


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAllAddBrandsFormComes() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/brands/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("brand-add"));


    }


    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddBrandSuccessfullySameModel() throws Exception {
        when(mockService.checkForSameBrand(any(AddBrandDTO.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/brands/add")
                        .param("brandName","BMW")
                        .param("imageURL","https://test")
                        .param("category","CAR")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/brands"));


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddBrandUnsuccessfullySameModel() throws Exception {



        when(mockService.checkForSameBrand(any(AddBrandDTO.class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/brands/add")
                        .param("brand","BMW  CAR")
                        .param("category","CAR")
                        .param("name","e90")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/brands/add"));


    }

}