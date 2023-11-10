package com.example.service.impl;

import com.example.model.dto.StandardDTO;
import com.example.model.entity.EuroStandardEntity;
import com.example.model.entity.enums.EuroStandardEnum;
import com.example.repository.EuroStandardRepository;
import com.example.service.EuroStandardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EuroStandardServiceImpl implements EuroStandardService {

    private final EuroStandardRepository repository;
    private final ModelMapper mapper;

    public EuroStandardServiceImpl(EuroStandardRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<StandardDTO> getAllStandardsNames() {
       return repository.findAll().stream().map(s->  mapper.map(s, StandardDTO.class)).toList();
    }

    @Override
    public EuroStandardEntity getStandard(String standard) {
        return repository.findByStandard(EuroStandardEnum.valueOf(standard)).orElse(null);
    }
}
