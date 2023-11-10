package com.example.service;

import com.example.model.dto.StandardDTO;
import com.example.model.entity.EuroStandardEntity;

import java.util.List;

public interface EuroStandardService {


   List<StandardDTO> getAllStandardsNames();


   EuroStandardEntity getStandard(String standard);
}
