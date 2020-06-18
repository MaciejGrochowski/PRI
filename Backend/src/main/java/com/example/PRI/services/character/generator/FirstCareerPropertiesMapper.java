package com.example.PRI.services.character.generator;

import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.PlaceType;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;

import java.util.HashMap;
import java.util.Map;

public class FirstCareerPropertiesMapper {

    public static Map<String, String> map(Character character, Map<String, String> properties) {
        Map<String, String> output = new HashMap<>();
        Place birthPlace = character.getBirthPlace();
        if (birthPlace == null || character.getSex() == null || character.getRace() == null) return output;

        if(!birthPlace.getPlaceType().equals(PlaceType.VILLIAGE))
            output.put("Akolita", "0.005");
        if(!birthPlace.getPlaceType().equals(PlaceType.VILLIAGE) && character.getSex()!=null && character.getSex().equals(Sex.MALE) && character.getRace()!=null && character.getRace().equals(Race.HUMAN))
            output.put("Akolita", "0.008");

        output.put("Banita", getOutlawChance(birthPlace, properties));
        if(birthPlace.getPlaceType().equals(PlaceType.VILLIAGE)) output.put("Chłop", "0.14");
        else output.put("Chłop", "0.03");
        output.put("Ciura obozowa", "0.02");

        output.put("Cyrkowiec", getEntertainerChance(birthPlace, properties));
        output.put("Cyrulik", "0.015");
        output.put("Fanatyk", "0.005");
        if(properties.containsKey("isRiver")) output.put("Flisak", "0.08");
        if(character.getSurname() != null && character.getRace()!=null && character.getRace().equals(Race.HUMAN)){
            if(character.getSurname().isGentry()) output.put("Giermek", "0.1");
            else output.put("Giermek", "0.01");
        }
        output.put("Gladiator", getGladiatorChance(birthPlace, properties));
        if(properties.containsKey("isMountains")) output.put("Górnik", "0.05");
        output.put("Guślarz", getHedgeChance(birthPlace, properties));
        output.put("Hiena cmentarna", "0.005");
        output.put("Kanciarz", getKanciarzChance(birthPlace, properties));
        if(properties.containsKey("isForest")) output.put("Leśnik", "0.06");
        if(properties.containsKey("isForest")) output.put("Łowca", "0.05");
        output.put("Łowca nagród", getLowcaNagrodChance(birthPlace, properties));

        if(character.getBirthPlace().getPlaceType().equals(PlaceType.CITY) ||
                character.getBirthPlace().getPlaceType().equals(PlaceType.CITYSTATE) ||
                character.getBirthPlace().getPlaceType().equals(PlaceType.TOWN)) output.put("Mieszczanin", "0.12");
        else if (character.getBirthPlace().getPlaceType().equals(PlaceType.SMALLTOWN)) output.put("Mieszczanin", "0.04");

        output.put("Mytnik", getMytnikChance(birthPlace, properties));
        output.put("Najemnik", getNajemnikChance(birthPlace, properties));
        output.put("Ochotnik", getOchotnikChance(birthPlace, properties));
        output.put("Ochroniarz", getOchroniarzChance(birthPlace, properties));
        output.put("Oprych", getOprychChance(birthPlace, properties));

        output.put("Paź", getPazChance(birthPlace, properties));
        output.put("Podżegacz", "0.01");
        output.put("Porywacz zwłok", "0.002");

        if(properties.containsKey("isRoad")) output.put("Posłaniec", "0.01");
        if(properties.containsKey("isPopularRoad")) output.put("Posłaniec", "0.025");
        output.put("Przemytnik", "0.01");
        output.put("Przepatrywacz", getPrzepatrywaczChance(birthPlace, properties));

        if(properties.containsKey("isSea")) output.put("Przewoźnik", "0.01");
        if(properties.containsKey("isRiver")) output.put("Przewoźnik", "0.05");
        if(properties.containsKey("isRiver")) output.put("Rybak", "0.03");
        if(properties.containsKey("isSea")) output.put("Rybak", "0.07");

        output.put("Rzecznik rodu", getRzecznikRoduChance(birthPlace, properties));
        output.put("Rzemieślnik", getRzemieslnikChance(birthPlace, properties));
        output.put("Rzezimieszek", getRzezimieszekChance(birthPlace, properties));
        output.put("Skryba", getSkrybaChance(birthPlace, properties));
        if(character.getBirthPlace().getPlaceType().equals(PlaceType.VILLIAGE)) output.put("Sługa", "0.005");
        else output.put("Sługa", "0.015");
        output.put("Strażnik", getStraznikChance(birthPlace, properties));
        if(properties.containsKey("isRoad")) output.put("Strażnik dróg", "0.005");
        if(properties.containsKey("isPopularRoad")) output.put("Strażnik dróg", "0.02");
        output.put("Strażnik więzienny", getStraznikWieziennyChance(birthPlace, properties));
        if(character.getBirthPlace().getPlaceType().equals(PlaceType.VILLIAGE)) output.put("Szczurołap", "0.01");
        output.put("Szczurołap", "0.02");
        if(character.getRace()!=null && character.getRace().equals(Race.HUMAN) && character.getSurname() != null){
            if(character.getSurname().isGentry()) output.put("Szlachcic", "0.12");
            else output.put("Szlachcic", "0.01");
        }

        output.put("Śmieciarz", getSmieciarzChance(birthPlace, properties));
        output.put("Uczeń czarodzieja", getUczenCzarodziejaChance(birthPlace, properties));
        output.put("Węglarz", getWeglarzChance(birthPlace, properties));
        output.put("Włóczykij", getWloczykijChance(birthPlace, properties));
        if(character.getBirthPlace().getPlaceType().equals(PlaceType.ELFPLACES)) output.put("Wojownik klanowy", "0.1");
        output.put("Woźnica", getWoznicaChance(birthPlace, properties));
        output.put("Zarządca", getZarzadcaChance(birthPlace, properties));
        output.put("Złodziej", getZlodziejChance(birthPlace, properties));
        output.put("Żak", getZakChance(birthPlace, properties));
        if(properties.containsKey("isRiver")) output.put("Żeglarz", "0.005");
        if(properties.containsKey("isSea")) output.put("Żeglarz", "0.05");
        output.put("Żołnierz", "0.035");
        if(properties.containsKey("isRiver")) output.put("Żołnierz okrętowy", "0.005");
        if(properties.containsKey("isSea")) output.put("Żołnierz okrętowy", "0.035");
        return output;
    }

    private static String getZakChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.04;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.001;
        if(properties.containsKey("isCapital")) chance += 0.005;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getZlodziejChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.005;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.005;
        if(properties.containsKey("isPopularRoad")) chance += 0.005;
        if(properties.containsKey("isCapital")) chance += 0.005;
        return String.valueOf(chance);
    }

    private static String getZarzadcaChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0005;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.008;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.005;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance += 0.005;
        return String.valueOf(chance);
    }

    private static String getWoznicaChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0;
        if(properties.containsKey("isPopularRoad")) chance += 0.04;
        if(properties.containsKey("isRoad")) chance += 0.03;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getWloczykijChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.005;
        if(birthPlace.getPlaceType().equals(PlaceType.VILLIAGE)) chance+=0.01;
        if(properties.containsKey("isPopularRoad")) chance+=0.01;
        if(properties.containsKey("isForest")) chance+=0.005;

        return String.valueOf(chance);
    }

    private static String getWeglarzChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0;

        if(birthPlace.getPlaceType().equals(PlaceType.VILLIAGE) && properties.containsKey("isForest")) chance += 0.02;
        if(properties.containsKey("isForest")) chance+=0.015;
        if(birthPlace.getPlaceType().equals(PlaceType.VILLIAGE)) chance+=0.015;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getUczenCzarodziejaChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.001;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance += 0.05;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getSmieciarzChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.02;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance += 0.001;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getStraznikWieziennyChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.015;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance += 0.005;
        return String.valueOf(chance);
    }

    private static String getStraznikChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.005;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.04;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.035;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance += 0.02;
        if(properties.containsKey("isPopularRoad")) chance += 0.01;
        if(properties.containsKey("isCapital")) chance += 0.01;
        if(properties.containsKey("isSwamp")) chance += 0.01;
        if(properties.containsKey("isMountains")) chance += 0.01;
        return String.valueOf(chance);

    }

    private static String getSkrybaChance(Place birthPlace, Map<String, String> properties) {
        double chance=0.001;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(properties.containsKey("isPopularRoad")) chance += 0.01;
        if(properties.containsKey("isCapital")) chance += 0.01;
        return String.valueOf(chance);
    }

    private static String getRzezimieszekChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.005;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.005;
        if(properties.containsKey("isPopularRoad")) chance += 0.005;
        if(properties.containsKey("isCapital")) chance += 0.005;
        return String.valueOf(chance);

    }

    private static String getRzemieslnikChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.015;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.02;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(properties.containsKey("isPopularRoad")) chance += 0.01;
        if(properties.containsKey("isCapital")) chance += 0.01;
        return String.valueOf(chance);
    }

    private static String getRzecznikRoduChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.08;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(properties.containsKey("isSea")) chance+= 0.005;
        return String.valueOf(chance);
    }

    private static String getPrzepatrywaczChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.005;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.005;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.005;
        if(properties.containsKey("isPopularRoad")) chance += 0.005;
        if(properties.containsKey("isCapital")) chance += 0.005;
        return String.valueOf(chance);
    }

    private static String getPazChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.03;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.03;
        if(properties.containsKey("isCapital")) chance += 0.02;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);

    }

    private static String getOprychChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.04;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(properties.containsKey("isPopularRoad")) chance += 0.01;
        if(properties.containsKey("isCapital")) chance += 0.02;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getOchroniarzChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.025;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance += 0.005;

        if(properties.containsKey("isPopularRoad")) chance += 0.01;
        if(properties.containsKey("isRoad")) chance += 0.005;
        if(properties.containsKey("isCapital")) chance += 0.015;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);

    }

    private static String getOchotnikChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance += 0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.VILLIAGE)) chance += 0.05;
        if(properties.containsKey("isSwamp")) chance += 0.005;
        if(properties.containsKey("isForest")) chance += 0.005;
        if(properties.containsKey("isMountains")) chance += 0.005;
        if(properties.containsKey("isHills")) chance += 0.005;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);

    }

    private static String getNajemnikChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.04;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.03;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance += 0.02;
        if(birthPlace.getPlaceType().equals(PlaceType.VILLIAGE)) chance += 0.01;

        if(properties.containsKey("isPopularRoad")) chance += 0.01;
        if(properties.containsKey("isRoad")) chance += 0.005;
        if(properties.containsKey("isCapital")) chance += 0.015;
        if(properties.containsKey("isSwamp")) chance += 0.005;
        if(properties.containsKey("isForest")) chance += 0.005;
        if(properties.containsKey("isMountains")) chance += 0.005;
        if(properties.containsKey("isHills")) chance += 0.005;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);

    }

    private static String getMytnikChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(properties.containsKey("isPopularRoad")) chance += 0.015;
        if(properties.containsKey("isRoad")) chance += 0.007;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getLowcaNagrodChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;

        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance+=0.02;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance += 0.005;

        if(properties.containsKey("isPopularRoad")) chance += 0.02;
        if(properties.containsKey("isRoad")) chance += 0.001;
        if(properties.containsKey("isCapital")) chance += 0.015;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getKanciarzChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(properties.containsKey("isRoad")) chance+=0.005;
        if(properties.containsKey("isPopularRoad")) chance+=0.01;
        if(properties.containsKey("isCapital")) chance+=0.005;
        if(birthPlace.getPlaceType().equals(PlaceType.CITYSTATE) || birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.005;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getHedgeChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(properties.containsKey("isSwamp")) chance+=0.03;
        if(properties.containsKey("isForest")) chance+=0.01;
        if(properties.containsKey("isHill")) chance += 0.005;
        if(!properties.containsKey("isRoad") && !properties.containsKey("isPopularRoad")) chance+=0.025;
        if(birthPlace.getPlaceType().equals(PlaceType.VILLIAGE)) chance+=0.01;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getGladiatorChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(properties.containsKey("isPopularRoad")) chance+=0.01;
        if(properties.containsKey("isCapital")) chance+=0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance += 0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.005;
        if(chance==0) chance=0.00001;
        return String.valueOf(chance);
    }

    private static String getEntertainerChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(properties.containsKey("isPopularRoad")) chance+=0.02;
        if(properties.containsKey("isCapital")) chance+=0.01;
        if(birthPlace.getPlaceType().equals(PlaceType.CITY) || birthPlace.getPlaceType().equals(PlaceType.CITYSTATE)) chance += 0.02;
        if(birthPlace.getPlaceType().equals(PlaceType.TOWN)) chance += 0.01;
        if(chance==0) chance=0.0001;
        return String.valueOf(chance);
    }

    private static String getOutlawChance(Place birthPlace, Map<String, String> properties) {
        double chance = 0.0;
        if(properties.containsKey("isForest")) chance+=0.02;
        if(birthPlace.getPlaceType().equals(PlaceType.VILLIAGE)) chance+=0.02;
        if(birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance +=0.005;
        if(!birthPlace.getPlaceType().equals(PlaceType.VILLIAGE) && !birthPlace.getPlaceType().equals(PlaceType.SMALLTOWN)) chance-= 0.015;
        if(properties.containsKey("isMountains")) chance+=0.01;
        if(!properties.containsKey("isRoad") && !properties.containsKey("isPopularRoad")) chance+=0.01;
        if(properties.containsKey("isCapital")) chance -= 0.01;
        if(properties.containsKey("isSwamp")) chance += 0.01;
        if(properties.containsKey("isHill")) chance+= 0.005;
        if(chance<=0.0) chance = 0.0001;
        return String.valueOf(chance);
    }
}
