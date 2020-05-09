package com.example.PRI.jsonDeserializationController;

import com.example.PRI.entities.character.Name;
import com.example.PRI.entities.character.Surname;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.PRI.services.character.NameService;
import com.example.PRI.services.character.SurnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class DeserializationController {

    @Autowired
    NameService nameService;

    @Autowired
    SurnameService surnameService;

    @RequestMapping("/json/name")
    public void nameDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = "src/jsons/name.json";
        String contents = Files.readString(Paths.get(path));
        List<Name> listName = objectMapper.readValue(contents, new TypeReference<List<Name>>() {
        });
        for (Name name : listName) {
            nameService.save(name);
        }
    }

    @RequestMapping("/json/surname")
    public void surnameDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = "src/jsons/surname.json";
        String contents = Files.readString(Paths.get(path));
        List<Surname> listSurname = objectMapper.readValue(contents, new TypeReference<List<Surname>>() {
        });
        for (Surname surName : listSurname) {
            surnameService.save(surName);
        }
    }
}

