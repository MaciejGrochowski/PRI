package com.example.PRI.services.character;

import com.example.PRI.entities.character.HairColor;
import com.example.PRI.repositories.character.HairColorRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HairColorService extends GeneralService {

    @Autowired
    HairColorRepository hairColorRepository;

    public void save(HairColor h){hairColorRepository.save(h);}

    public Optional<HairColor> findByName(String hairColor) {
        return Optional.ofNullable(hairColorRepository.findByColor(hairColor));
    }
}
