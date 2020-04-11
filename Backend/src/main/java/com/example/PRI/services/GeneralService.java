package com.example.PRI.services;

import com.example.PRI.entities.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {

    @Autowired
    PlaceService placeService;

}