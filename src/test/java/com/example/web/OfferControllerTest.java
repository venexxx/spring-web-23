package com.example.web;

import com.example.model.dto.BrandAndModelBindingModel;
import com.example.model.dto.UserBindingModel;
import com.example.model.dto.UserRegistrationDTO;
import com.example.model.entity.UserEntity;
import com.example.model.entity.enums.ModelCategoryEnum;
import com.example.repository.UserRepository;
import com.example.service.BrandService;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  BrandService brandService;

    @Test
    public void testOfferCatalogComes() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/offers/catalog"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-offer-catalog"));


    }




    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddCarOfferFormComes() throws Exception {
        BrandAndModelBindingModel first = new BrandAndModelBindingModel();
        first.setName("BMW");
        first.setCategory("Car");
        first.setModels(Set.of("e90","535"));
        BrandAndModelBindingModel second = new BrandAndModelBindingModel();
        second.setName("Audi");
        second.setCategory("Car");
        second.setModels(Set.of("a4","rs6"));

        Set<BrandAndModelBindingModel> categoriesActual = new HashSet<>();
        categoriesActual.add(first);
        categoriesActual.add(second);

        when(brandService.getAllBrandsAndModels(ModelCategoryEnum.CAR)).thenReturn(categoriesActual);

        mockMvc.perform(MockMvcRequestBuilders.get("/offers/add-car"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-car-offer"))
                .andExpect(model().attribute("carBrands",categoriesActual ));


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddCarOfferSuccessfully() throws Exception {
        BrandAndModelBindingModel first = new BrandAndModelBindingModel();
        first.setName("BMW");
        first.setCategory("Car");
        first.setModels(Set.of("e90","535"));
        BrandAndModelBindingModel second = new BrandAndModelBindingModel();
        second.setName("Audi");
        second.setCategory("Car");
        second.setModels(Set.of("a4","rs6"));

        Set<BrandAndModelBindingModel> categoriesActual = new HashSet<>();
        categoriesActual.add(first);
        categoriesActual.add(second);

        when(brandService.getAllBrandsAndModels(ModelCategoryEnum.CAR)).thenReturn(categoriesActual);

        mockMvc.perform(MockMvcRequestBuilders.post("/offers/add-car")
                        .param("description","test")
                        .param("engine","PETROL")
                        .param("transmission","AUTOMATIC")
                        .param("imageUrl","http://test")
                        .param("secondImageUrl","http://test2")
                        .param("mileage","20000")
                        .param("price","20000")
                        .param("year","2020")
                        .param("horsePower","135")
                        .param("cubicCentimeters","3000")
                        .param("conditionName","NEW")
                        .param("standard","EURO_1")
                        .param("model","BMW e90")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/search/CAR"));


    }
    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddCarOfferUnSuccessfully() throws Exception {
        BrandAndModelBindingModel first = new BrandAndModelBindingModel();
        first.setName("BMW");
        first.setCategory("Car");
        first.setModels(Set.of("e90","535"));
        BrandAndModelBindingModel second = new BrandAndModelBindingModel();
        second.setName("Audi");
        second.setCategory("Car");
        second.setModels(Set.of("a4","rs6"));

        Set<BrandAndModelBindingModel> categoriesActual = new HashSet<>();
        categoriesActual.add(first);
        categoriesActual.add(second);

        when(brandService.getAllBrandsAndModels(ModelCategoryEnum.CAR)).thenReturn(categoriesActual);

        mockMvc.perform(MockMvcRequestBuilders.post("/offers/add-car")
                        .param("description","test")
                        .param("engine","PETROL")
                        .param("transmission","AUTOMATIC")
                        .param("imageUrl","http://test")
                        .param("secondImageUrl","http://test2")
                        .param("mileage","20000")
                        .param("price","20000")
                        .param("year","2020")
                        .param("horsePower","135")
                        .param("cubicCentimeters","3000")
                        .param("conditionName","NEW")
                        .param("standard","EURO_1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/add-car"));


    }


    @Test
    public void testAddCarOfferFormRedirect() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/offers/add-car"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/users/login"));


    }

    @Test
    public void testAddTruckOfferFormRedirect() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/offers/add-truck"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/users/login"));


    }


    @Test
    public void testAddMotorCycleOfferFormRedirect() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/offers/add-motorcycle").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/users/login"));


    }


    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddTruckOfferFormComes() throws Exception {
        BrandAndModelBindingModel first = new BrandAndModelBindingModel();
        first.setName("Scania");
        first.setCategory("Truck");
        first.setModels(Set.of("r450","v700"));
        BrandAndModelBindingModel second = new BrandAndModelBindingModel();
        second.setName("Volvo");
        second.setCategory("Truck");
        second.setModels(Set.of("test500","test450"));

        Set<BrandAndModelBindingModel> categoriesActual = new HashSet<>();
        categoriesActual.add(first);
        categoriesActual.add(second);

        when(brandService.getAllBrandsAndModels(ModelCategoryEnum.TRUCK)).thenReturn(categoriesActual);

        mockMvc.perform(MockMvcRequestBuilders.get("/offers/add-truck"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-truck-offer"))
                .andExpect(model().attribute("truckBrands",categoriesActual ));


    }
    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddTruckOfferSuccessfully() throws Exception {
        BrandAndModelBindingModel first = new BrandAndModelBindingModel();
        first.setName("Scania");
        first.setCategory("Truck");
        first.setModels(Set.of("r450","v700"));
        BrandAndModelBindingModel second = new BrandAndModelBindingModel();
        second.setName("Volvo");
        second.setCategory("Truck");
        second.setModels(Set.of("test500","test450"));

        Set<BrandAndModelBindingModel> categoriesActual = new HashSet<>();
        categoriesActual.add(first);
        categoriesActual.add(second);

        when(brandService.getAllBrandsAndModels(ModelCategoryEnum.TRUCK)).thenReturn(categoriesActual);

        mockMvc.perform(MockMvcRequestBuilders.post("/offers/add-truck")
                        .param("description","test")
                        .param("transmission","AUTOMATIC")
                        .param("imageUrl","http://test")
                        .param("secondImageUrl","http://test2")
                        .param("mileage","20000")
                        .param("price","20000")
                        .param("year","2020")
                        .param("horsePower","135")
                        .param("cubicCentimeters","3000")
                        .param("conditionName","NEW")
                        .param("standard","EURO_1")
                        .param("axles","Two")
                        .param("model","Volvo test450")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/search/TRUCK"));


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddTruckOfferUnSuccessfully() throws Exception {
        BrandAndModelBindingModel first = new BrandAndModelBindingModel();
        first.setName("Scania");
        first.setCategory("Truck");
        first.setModels(Set.of("r450","v700"));
        BrandAndModelBindingModel second = new BrandAndModelBindingModel();
        second.setName("Volvo");
        second.setCategory("Truck");
        second.setModels(Set.of("test500","test450"));

        Set<BrandAndModelBindingModel> categoriesActual = new HashSet<>();
        categoriesActual.add(first);
        categoriesActual.add(second);

        when(brandService.getAllBrandsAndModels(ModelCategoryEnum.TRUCK)).thenReturn(categoriesActual);

        mockMvc.perform(MockMvcRequestBuilders.post("/offers/add-truck")
                        .param("description","test")
                        .param("transmission","AUTOMATIC")
                        .param("secondImageUrl","http://test2")
                        .param("mileage","20000")
                        .param("price","20000")
                        .param("year","2020")
                        .param("horsePower","135")
                        .param("cubicCentimeters","3000")
                        .param("conditionName","NEW")
                        .param("standard","EURO_1")
                        .param("axles","Two")
                        .param("model","Volvo test450")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/add-truck"));


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddMotorcycleOfferFormComes() throws Exception {
        BrandAndModelBindingModel first = new BrandAndModelBindingModel();
        first.setName("Honda");
        first.setCategory("Motorcycle");
        first.setModels(Set.of("crf250x","crf450r"));
        BrandAndModelBindingModel second = new BrandAndModelBindingModel();
        second.setName("KTM");
        second.setCategory("Motorcycle");
        second.setModels(Set.of("exc300","ec450"));

        Set<BrandAndModelBindingModel> categoriesActual = new HashSet<>();
        categoriesActual.add(first);
        categoriesActual.add(second);

        when(brandService.getAllBrandsAndModels(ModelCategoryEnum.MOTORCYCLE)).thenReturn(categoriesActual);

        mockMvc.perform(MockMvcRequestBuilders.get("/offers/add-motorcycle"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-motorcycle-offer"))
                .andExpect(model().attribute("motorcycleBrands",categoriesActual ));


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddMotorcycleOfferSuccessfully() throws Exception {
        BrandAndModelBindingModel first = new BrandAndModelBindingModel();
        first.setName("Honda");
        first.setCategory("Motorcycle");
        first.setModels(Set.of("crf250x","crf450r"));
        BrandAndModelBindingModel second = new BrandAndModelBindingModel();
        second.setName("KTM");
        second.setCategory("Motorcycle");
        second.setModels(Set.of("exc300","ec450"));

        Set<BrandAndModelBindingModel> categoriesActual = new HashSet<>();
        categoriesActual.add(first);
        categoriesActual.add(second);

        when(brandService.getAllBrandsAndModels(ModelCategoryEnum.MOTORCYCLE)).thenReturn(categoriesActual);

        mockMvc.perform(MockMvcRequestBuilders.post("/offers/add-motorcycle")
                        .param("description","test")
                        .param("imageUrl","http://test")
                        .param("secondImageUrl","http://test2")
                        .param("mileage","20000")
                        .param("price","20000")
                        .param("transmission","MANUAL")
                        .param("year","2020")
                        .param("horsePower","135")
                        .param("cubicCentimeters","3000")
                        .param("conditionName","NEW")
                        .param("standard","EURO_1")
                        .param("motoHours","120")
                        .param("model","KTM exc300")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/search/MOTORCYCLE"));


    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddMotorcycleUnOfferSuccessfully() throws Exception {
        BrandAndModelBindingModel first = new BrandAndModelBindingModel();
        first.setName("Honda");
        first.setCategory("Motorcycle");
        first.setModels(Set.of("crf250x","crf450r"));
        BrandAndModelBindingModel second = new BrandAndModelBindingModel();
        second.setName("KTM");
        second.setCategory("Motorcycle");
        second.setModels(Set.of("exc300","ec450"));

        Set<BrandAndModelBindingModel> categoriesActual = new HashSet<>();
        categoriesActual.add(first);
        categoriesActual.add(second);

        when(brandService.getAllBrandsAndModels(ModelCategoryEnum.MOTORCYCLE)).thenReturn(categoriesActual);

        mockMvc.perform(MockMvcRequestBuilders.post("/offers/add-motorcycle")
                        .param("description","test")
                        .param("imageUrl","http://test")
                        .param("mileage","20000")
                        .param("price","20000")
                        .param("transmission","MANUAL")
                        .param("year","2020")
                        .param("horsePower","135")
                        .param("cubicCentimeters","3000")
                        .param("conditionName","NEW")
                        .param("standard","EURO_1")
                        .param("motoHours","120")
                        .param("model","KTM exc300")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/add-motorcycle"));


    }


}


