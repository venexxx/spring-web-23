package com.example.model.entity;

import com.example.model.entity.enums.EuroStandardEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Table(name = "standarts")
@Entity
public class EuroStandardEntity extends BaseEntity{

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private EuroStandardEnum standard;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EuroStandardEnum getStandard() {
        return standard;
    }

    public void setStandard(EuroStandardEnum standard) {
        this.standard = standard;
    }
}
