package com.example.PRI.services.character;

import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.EyeColor;
import com.example.PRI.entities.character.Name;
import com.example.PRI.repositories.character.EyeColorRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EyeColorService extends GeneralService {

    @Autowired
    EyeColorRepository eyeColorRepository;

    public void save(EyeColor ey){
        EyeColor eyesC = eyeColorRepository.findByColor(ey.getColor());
        if(eyesC != null) ey.setId(eyesC.getId());
        eyeColorRepository.save(ey);
    }

    public Optional<EyeColor> findByName(String eyeColor) {
        return Optional.ofNullable(eyeColorRepository.findByColor(eyeColor));
    }

    public List<EyeColor> findAll() {
        return eyeColorRepository.findAll();
    }

    public List<String> getAllColors() {
        return findAll().stream().map(EyeColor::getColor).collect(Collectors.toList());
    }
}
