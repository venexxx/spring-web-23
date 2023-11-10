package com.example.init;

import com.example.model.entity.EuroStandardEntity;
import com.example.model.entity.enums.EuroStandardEnum;
import com.example.repository.EuroStandardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EuroStandardInit implements CommandLineRunner {
    private final EuroStandardRepository repository;

    public EuroStandardInit(EuroStandardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<EuroStandardEntity> standards = new ArrayList<>();

        if (!(repository.count() > 0)){
            Arrays.stream(EuroStandardEnum.values()).forEach(s ->{
                EuroStandardEntity standard = new EuroStandardEntity();
                standard.setStandard(s);
                String name = "";
                switch (s){
                    case EURO_1 -> name = "Euro 1";
                    case EURO_2 -> name = "Euro 2";
                    case EURO_3 -> name = "Euro 3";
                    case EURO_4 -> name = "Euro 4";
                    case EURO_5 -> name = "Euro 5";
                    case EURO_6 -> name = "Euro 6";
                }
                standard.setName(name);
                standards.add(standard);
            });
        }
        repository.saveAll(standards);

    }
}
