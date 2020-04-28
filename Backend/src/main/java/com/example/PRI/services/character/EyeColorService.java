package com.example.PRI.services.character;

import com.example.PRI.entities.character.EyeColor;
import com.example.PRI.repositories.character.EyeColorRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EyeColorService extends GeneralService {


    @Autowired
    EyeColorRepository eyeColorRepository;

    public Optional<EyeColor> findByName(String eyeColor) {
        return Optional.ofNullable(eyeColorRepository.findByColor(eyeColor));
    }
}
