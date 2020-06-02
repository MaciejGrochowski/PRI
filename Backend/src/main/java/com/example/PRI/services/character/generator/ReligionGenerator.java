package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.PlaceType;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;

import java.util.*;
import java.util.stream.Collectors;

public class ReligionGenerator {


    public Map<String, String> generateReligion(Character character, HashMap<String, String> properties) {
        List<String> religionList = Arrays.stream(Religion.values()).map(Religion::getGodName).collect(Collectors.toList());;

        List<String> propertiesKeySetReligions = properties.keySet().stream().filter(religionList::contains).collect(Collectors.toList());

        Map<String, String> religionProperties = new HashMap<>();
        double maxRandomRoll = 0.0;

        for(String religionKey : propertiesKeySetReligions){
            maxRandomRoll += Double.parseDouble(properties.get(religionKey));
            religionProperties.put(religionKey, properties.get(religionKey));
        }
        double randomRoll = new Random().nextDouble() * maxRandomRoll;
        for(String religionKey : religionProperties.keySet()){
            randomRoll -= Double.parseDouble(religionProperties.get(religionKey));
            if(randomRoll <= 0) {
                character.setReligion(Religion.findByGodName(religionKey));
                break;
            }
        }

        this.validateReligion(character);

        return MapperJsonStringToMap.mapJsonStringToMap(character.getReligion().getProperties());
    }

    private void validateReligion(Character character) {

        if(character.getSex().equals(Sex.MALE) && character.getReligion().equals(Religion.SHALLYA) &&
                character.getCurrentCareer().getName().equals("Akolita") ||
                character.getCurrentCareer().getName().equals("Kapłan") ||
               character.getCurrentCareer().getName().equals("Wybraniec boży") ||
                character.getCurrentCareer().getName().equals("Arcykapłan")
        ) character.setReligion(Religion.SIGMAR);

        if(character.getSex().equals(Sex.FEMALE) && character.getReligion().equals(Religion.SIGMAR) ||
                character.getReligion().equals(Religion.ULRIC) &&
                character.getCurrentCareer().getName().equals("Akolita") ||
                character.getCurrentCareer().getName().equals("Kapłan") ||
                character.getCurrentCareer().getName().equals("Wybraniec boży") ||
                character.getCurrentCareer().getName().equals("Arcykapłan")
        ) character.setReligion(Religion.SHALLYA);


        if(character.getRace().equals(Race.HUMAN)){
            if(character.getReligion().equals(Religion.MATHLANN)) character.setReligion(Religion.MANANN);
            if(character.getReligion().equals(Religion.SARRIEL) || character.getReligion().equals(Religion.GAZUL)) character.setReligion(Religion.MORR);
        }

        if(character.getRace().equals(Race.ELF)){
            if(character.getReligion().equals(Religion.MANANN)) character.setReligion(Religion.MATHLANN);
            if(character.getReligion().equals(Religion.MORR) || character.getReligion().equals(Religion.GAZUL)) character.setReligion(Religion.SARRIEL);
            if(character.getReligion().equals(Religion.SIGMAR) || character.getReligion().equals(Religion.ULRIC)) character.setReligion(Religion.ASURYAN);
            if(character.getReligion().equals(Religion.TAALRHYA)){
                if(new Random().nextDouble()<0.5) character.setReligion(Religion.KURNOUS);
                else character.setReligion(Religion.ISHA);
            }
            if(character.getReligion().equals(Religion.VERENA)) character.setReligion(Religion.HOETH);
        }

        if(character.getRace().equals(Race.DWARF)){
            if(character.getReligion().equals(Religion.SARRIEL) || character.getReligion().equals(Religion.MORR)) character.setReligion(Religion.GAZUL);
            if(character.getReligion().equals(Religion.SIGMAR)) character.setReligion(Religion.GRUNGNI);
            if(character.getReligion().equals(Religion.ULRIC)) character.setReligion(Religion.GRIMNIR);
            if(character.getReligion().equals(Religion.TAALRHYA)) character.setReligion(Religion.VALAYA);
        }

        if(character.getRace().equals(Race.HALFLING)){
            if(character.getReligion().equals(Religion.ULRIC) || character.getReligion().equals(Religion.TAALRHYA)) character.setReligion(Religion.ESMERALDA);
        }

    }

    public Map<String, String> prepareProps(Character character, HashMap<String, String> properties) {
        Map<String, String> output = new HashMap<>();

        if(character.getRace().equals(Race.HUMAN)){
            if(character.getBirthPlace().getPlaceType().equals(PlaceType.VILLIAGE)) output.put("Stara Wiara", "0.002");
            else output.put("Stara Wiara", "0.0005");
            output.put("Tzeentch", "0.003");
            output.put("Nurgle", "0.003");
            output.put("Slaanesh", "0.003");
            output.put("Khorne", "0.001");
        }
        output.put("Manann", "0.001");
        if(properties.containsKey("isRiver")) output.put("Manann", "0.05");
        if(properties.containsKey("isSea")) output.put("Manann", "0.15");

        output.put("Morr", "0.005");
        if(character.getRace().equals(Race.HUMAN)) output.put("Morr", "0.02");

        output.put("Myrmydia", "0.001");

        output.put("Ranald", "0.06");
        if(!character.getBirthPlace().getPlaceType().equals(PlaceType.VILLIAGE)) output.put("Ranald", "0.12");

        output.put("Shallya", "0.1");
        if(character.getSex().equals(Sex.FEMALE)) output.put("Shallya", "0.18");

        output.put("Sigmar", "0.001");
        if(character.getRace().equals(Race.HUMAN)) output.put("Sigmar", "0.1");


        double taalChance = 0.01;
        if(properties.containsKey("isForest")) taalChance+=0.04;
        if(character.getBirthPlace().getPlaceType().equals(PlaceType.VILLIAGE)) taalChance+=0.15;
        output.put("Taal&Rhya", String.valueOf(taalChance));

        output.put("Ulryk", "0.001");
        if(character.getRace().equals(Race.HUMAN)) output.put("Ulryk", "0.1");

        output.put("Verena", "0.05");
        if(character.getBirthPlace().getPlaceType().equals(PlaceType.CITYSTATE) || character.getBirthPlace().getPlaceType().equals(PlaceType.CITY))
            output.put("Verena", "0.1");

        output.put("Brak bóstwa opiekuńczego", "0.25");


        if(character.getRace().equals(Race.ELF)){
            output.put("Brak bóstwa opiekuńczego", "0.1");
            output.put("Asuryan", "0.2");
            output.put("Sarriel", "0.05");
            output.put("Khaine", "0.01");
            output.put("Isha", "0.04");
            output.put("Kurnous", "0.04");
            output.put("Hoeth", "0.05");
            output.put("Lileath", "0.04");
            output.put("Moraiheg", "0.04");
            output.put("Mathlann", "0.04");
        }

        if(character.getRace().equals(Race.DWARF)){
            output.put("Brak bóstwa opiekuńczego", "0.1");
            output.put("Grungni", "0.4");
            output.put("Grimnir", "0.25");
            output.put("Valaya", "0.2");
        }

        if(character.getRace().equals(Race.HALFLING)){
            output.put("Esmeralda", "0.2");
            output.put("Brak bóstwa opiekuńczego", "0.9");
        }
        return output;
    }

    public Map<String, String> getProperties(Character character, Religion religion) {
        return  MapperJsonStringToMap.mapJsonStringToMap(character.getReligion().getProperties());
    }
}
