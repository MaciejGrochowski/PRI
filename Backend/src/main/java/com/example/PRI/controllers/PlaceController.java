package com.example.PRI.controllers;


import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.services.PlaceService;
import com.example.PRI.services.character.CareerService;
import com.example.PRI.services.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/app/places")
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @Get("/names/all")
    public List<String> getAllPlaceNames() {
        return placeService.getAllNames();
    }



}
