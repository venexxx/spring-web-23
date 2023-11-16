package com.example.service.impl;

import com.example.model.dto.StandardDTO;
import com.example.model.entity.EuroStandardEntity;
import com.example.model.entity.UserEntity;
import com.example.model.entity.UserRoleEntity;
import com.example.model.entity.enums.EuroStandardEnum;
import com.example.model.entity.enums.UserRoleEnum;
import com.example.repository.EuroStandardRepository;
import com.example.service.EuroStandardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EuroStandardServiceImplTest {

    private EuroStandardServiceImpl serviceToTest;
    private  ModelMapper mapper = new ModelMapper();

    @Mock
    private EuroStandardRepository mockRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new EuroStandardServiceImpl(mockRepository,mapper) {
        };
    }

    @Test
    void testStandardsAreNotFound(){
        assertEquals(new ArrayList<>(),serviceToTest.getAllStandardsNames());
    }

    @Test
    void testEuroStandardsAreFound(){
        List<EuroStandardEntity> euroStandardEntities = createTestStandards();
        when(mockRepository.findAll()).thenReturn(createTestStandards());
        when(mockRepository.findByStandard(EuroStandardEnum.EURO_2)).thenReturn(Optional.of(createTestStandards().get(1)));


        List<StandardDTO> testAllNames = serviceToTest.getAllStandardsNames();
        EuroStandardEntity testStandard = serviceToTest.getStandard("EURO_2");

        assertFalse(testAllNames.isEmpty());

        assertEquals("Euro 1",testAllNames.get(0).getName());
        assertEquals("Euro 2",testStandard.getName());



    }

    @Test
    void testStandardAreNotFound(){
        assertNull(serviceToTest.getStandard("EURO_1"));
    }


    private static List<EuroStandardEntity> createTestStandards() {
        EuroStandardEntity euroStandardEntity1 = new EuroStandardEntity();
        euroStandardEntity1.setStandard(EuroStandardEnum.EURO_1);
        euroStandardEntity1.setName("Euro 1");
        euroStandardEntity1.setId(1L);


        EuroStandardEntity euroStandardEntity2 = new EuroStandardEntity();
        euroStandardEntity2.setStandard(EuroStandardEnum.EURO_1);
        euroStandardEntity2.setName("Euro 2");
        euroStandardEntity2.setId(2L);
        List<EuroStandardEntity> testList = new ArrayList<>();
        testList.add(euroStandardEntity1);
        testList.add(euroStandardEntity2);
        return testList;
    }



}