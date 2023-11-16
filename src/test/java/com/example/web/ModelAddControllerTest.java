package com.example.web;

import com.example.model.dto.AddModelDTO;
import com.example.model.dto.BrandAndModelBindingModel;
import com.example.model.entity.BrandEntity;
import com.example.model.entity.enums.ModelCategoryEnum;
import com.example.service.ModelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ModelAddControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelService modelService;


    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddModelFormComes() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/model-add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("model-add"));


    }


    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddModelSuccessfully() throws Exception {
        AddModelDTO mockModel = new AddModelDTO();
        mockModel.setBrand("BMW");
        mockModel.setCategory("CAR");
        mockModel.setName("e90");


        when(modelService.checkForTheSameModel(mockModel)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/model/add")
                        .param("brand","BMW  CAR")
                        .param("category","CAR")
                        .param("name","e90")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));


    }


    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddModelUnsuccessfullySameModel() throws Exception {
        AddModelDTO mockModel = new AddModelDTO();
        mockModel.setBrand("BMW");
        mockModel.setCategory("CAR");
        mockModel.setName("e90");


        when(modelService.checkForTheSameModel(any(AddModelDTO.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/model/add")
                        .param("brand","BMW  CAR")
                        .param("category","CAR")
                        .param("name","e90")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));


    }


    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddModelUnsuccessfully() throws Exception {
        AddModelDTO mockModel = new AddModelDTO();
        mockModel.setBrand("BMW");
        mockModel.setCategory("CAR");
        mockModel.setName("e90");


        when(modelService.checkForTheSameModel(mockModel)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/model/add")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/model-add"));


    }
}