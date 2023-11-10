package com.example.repository;

import com.example.model.entity.EuroStandardEntity;
import com.example.model.entity.enums.EuroStandardEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EuroStandardRepository extends JpaRepository<EuroStandardEntity,Long> {

    Optional<EuroStandardEntity> findByStandard(EuroStandardEnum standard);
}
