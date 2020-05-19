package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.EyeColor;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.EyeColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class EyeColorGenerator extends GeneralService {

    @Autowired
    EyeColorService eyeColorService;

    public Map<String, String> generateEyeColor(Character character, HashMap<String, String> properties) {
        List<EyeColor> eyeColors = eyeColorService.findAll();
        double randomRoll = new Random().nextDouble();

        for(EyeColor eyeColor : eyeColors){


        }


        return null;
    }
}
