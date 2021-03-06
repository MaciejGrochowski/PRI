package com.example.PRI.services.character;

import com.example.PRI.entities.character.EyeColor;
import com.example.PRI.entities.character.HairColor;
import com.example.PRI.repositories.character.HairColorRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HairColorService extends GeneralService {

    @Autowired
    HairColorRepository hairColorRepository;

    public void save(HairColor h){
        HairColor hairC = hairColorRepository.findByColor(h.getColor());
        if(hairC != null) h.setId(hairC.getId());
        hairColorRepository.save(h);
    }

    public Optional<HairColor> findByName(String hairColor) {
        return Optional.ofNullable(hairColorRepository.findByColor(hairColor));
    }

    public List<HairColor> findAll(){
        return hairColorRepository.findAll();
    }

    public List<String> getAllColors() {
        return findAll().stream().map(HairColor::getColor).collect(Collectors.toList());
    }
}
