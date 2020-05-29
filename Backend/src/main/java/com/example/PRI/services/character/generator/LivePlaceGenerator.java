package com.example.PRI.services.character.generator;

import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.PlaceType;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LivePlaceGenerator extends GeneralService {

    @Autowired
    PlaceService placeService;

    public Map<String, String> generateLivePlace(Character character, HashMap<String, String> properties) {
        double landJourneyChance = 0.1 + 0.03 * character.getPreviousCareers().size();
        double nationJourneyChance = 0.05 + 0.01 * character.getPreviousCareers().size();
        //ToDo przypadki specjalne dla Miejsc Elfów i Miejsc Krasnoludów, które nie mają parentów Landów!!

        if(properties.containsKey("landJourney")){
            landJourneyChance += Double.parseDouble(properties.get("landJourney"));
        }
        if(properties.containsKey("nationJourney")){
            nationJourneyChance += Double.parseDouble(properties.get("nationJourney"));
        }

        if(new Random().nextDouble() < nationJourneyChance){
            character.setLivePlace(this.generateLiveNationPlace(character, properties));
        }
        else if(new Random().nextDouble() < landJourneyChance){
            character.setLivePlace(this.generateLiveLandPlace(character, properties));
        }
        if(character.getLivePlace()==null) character.setLivePlace(character.getBirthPlace());
        return new HashMap<>();
    }

    private Place generateLiveLandPlace(Character character, HashMap<String, String> properties) {
        List<Place> probablyPlaces = placeService.findAll().stream().filter(p -> p.getParent()!=null && p.getParent().equals(character.getBirthPlace().getParent())).collect(Collectors.toList());
        return getPlace(character, probablyPlaces);
    }

    private Place randomPlace(List<Place> probablyPlaces) {
        Random rand = new Random();
        while(true) {
            double placeTypeRand = rand.nextDouble();
            PlaceType placeType;
            if (placeTypeRand < 0.12) placeType = PlaceType.VILLIAGE;
            else if (placeTypeRand < 0.26) placeType = PlaceType.SMALLTOWN;
            else if (placeTypeRand < 0.69) placeType = PlaceType.TOWN;
            else if (placeTypeRand < 0.77) placeType = PlaceType.CITY;
            else placeType = PlaceType.CITYSTATE;
            List<Place> maybe = probablyPlaces.stream().filter(p -> p.getPlaceType().equals(placeType)).collect(Collectors.toList());
            if(maybe.size() > 0) return maybe.get(rand.nextInt(maybe.size()));
        }
    }

    private Place getRandomPlaceByProperties(Career career, List<Place> probablyPlaces) {
        List<Place> placeToRandom = new ArrayList<>();
        if (career.getProperties().contains("isForest"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getProperties().contains("isForest")).collect(Collectors.toList()));
        if (career.getProperties().contains("isMountains"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getProperties().contains("isMountains")).collect(Collectors.toList()));
        if (career.getProperties().contains("isRiver"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getProperties().contains("isRiver")).collect(Collectors.toList()));
        if (career.getProperties().contains("isCapitol"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getProperties().contains("isCapitol")).collect(Collectors.toList()));
        if (career.getProperties().contains("isSwamp"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getProperties().contains("isSwamp")).collect(Collectors.toList()));
        if (career.getProperties().contains("isSea"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getProperties().contains("isSea")).collect(Collectors.toList()));
        if (career.getProperties().contains("isHill"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getProperties().contains("isHill")).collect(Collectors.toList()));
        if (career.getProperties().contains("isRoad"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getProperties().contains("isRoad")).collect(Collectors.toList()));
        if (career.getProperties().contains("isPopularRoad"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getProperties().contains("isPopularRoad")).collect(Collectors.toList()));
        if (career.getProperties().contains("isVillage"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> p.getPlaceType().equals(PlaceType.VILLIAGE)).collect(Collectors.toList()));
        if (career.getProperties().contains("isNotVilliage"))
            placeToRandom.addAll(probablyPlaces.stream().filter(p -> !p.getPlaceType().equals(PlaceType.VILLIAGE)).collect(Collectors.toList()));
        if(placeToRandom.size() > 0)return randomPlace(placeToRandom);
        else return randomPlace(probablyPlaces);
    }

    private Place generateLiveNationPlace(Character character, HashMap<String, String> properties) {
        List<Place> probablyPlaces = placeService.findAll().stream().filter(p -> p.getParent()!=null && !p.getParent().equals(character.getBirthPlace().getParent())).collect(Collectors.toList());
        return getPlace(character, probablyPlaces);
    }

    private Place getPlace(Character character, List<Place> probablyPlaces) {
        Random rand = new Random();
        double randomRoll = rand.nextDouble();
        if(randomRoll < 0.25) return this.randomPlace(probablyPlaces);
        Career career = character.getCurrentCareer();
        Place place = this.getRandomPlaceByProperties(career, probablyPlaces);
        if(place != null) return place;
        else return this.randomPlace(probablyPlaces);
    }
}
