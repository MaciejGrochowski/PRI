package com.example.PRI.services.character;

import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;
import com.example.PRI.enums.StarSign;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class CharacterSpecifications {
    public static Specification<Character> getByName(Name name) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    public static Specification<Character> getBySurname(Surname surname) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("surname"), surname);
        };
    }

    public static Specification<Character> getAll() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNotNull(root.get("name")); //ToDo lepszy warunek niż nullowe imię
        };
    }

    public static Specification<Character> GetNoone() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNull(root.get("name")); //ToDo lepszy warunek niż nullowe imię
        };
    }

//    public static Specification<Character> getByTalent(Talent talent) { //DZIAŁA!!
//        return (root, query, cb) -> {
//            Join<Character, Talent> talents = root.join("talents");
//            return cb.equal(talents.get("name"), talent.getName());
//            //ToDo identyczne zapytania dla innych cech postaci, które mogą być filtrowane przez obiekt
//        };
//    }

    public static Specification<Character> getByTalents(List<Talent> talentList){
        return (root, query, cb) -> {
            Join<Character, Talent> talents = root.join("talents");
            return talents.in(talentList); // DZIAŁA!!
            }; //ToDo identyczne zapytania dla innych cech postaci, które mogą być filtrowane przez listę.
    }

    public static Specification<Character> getByRace(Race race) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("race"), race);
        };
    }

    public static Specification<Character> getByEyeColor(EyeColor eyeColor) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("eyeColor"), eyeColor);
        };
    }

    public static Specification<Character> getByHairColor(HairColor hairColor) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("hairColor"), hairColor);
        };
    }

    public static Specification<Character> getByBirthPlace(Place place) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("birthPlace"), place);
        };
    }

    public static Specification<Character> getByStarSign(StarSign starSign) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("starSign"), starSign);
        };
    }

    public static Specification<Character> getByEmotions(List<Emotion> dominatingEmotions) {
        return (root, query, cb) -> {
            Join<Character, Emotion> talents = root.join("dominatingEmotions");
            return talents.in(dominatingEmotions);
        };
    }

    public static Specification<Character> getBySex(Sex sex) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("sex"), sex);
        };
    }

    public static Specification<Character> getByReligion(Religion religion) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("religion"), religion);
        };
    }

    public static Specification<Character> getByPrediction(Prediction prediction) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("prediction"), prediction);
        };
    }

    public static Specification<Character> getByCareer(List<Career> careers) {
        return (root, query, cb) -> {
            Join<Character, Career> career = root.join("currentCareer");
            return root.get("currentCareer").in(careers);
//            return career.in(careers);
        };
    }

    public static Specification<Character> getBySkills(List<Skill> skills) {
        return (root, query, cb) -> {
            Join<Character, Skill> career = root.join("skills");
            return career.in(skills);
        };
    }

    public static Specification<Character> getByPersonalities(List<Personality> personalities) {
        return (root, query, cb) -> {//ToDo te Joiny WYMAGAJĄ W root.join NAZWY z ENTITY!!!!!
            Join<Character, Personality> personality = root.join("personality");
            return personality.in(personalities);
        };
    }

    public static Specification<Character> getByApperances(List<Apperance> apperances) {
        return (root, query, cb) -> {
            Join<Character, Apperance> apperance = root.join("apperance");
            return apperance.in(apperances);
        };
    }

    public static Specification<Character> getByLivePlace(Place place) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("livePlace"), place);
        };
    }

    public static Specification<Character> getByLivePlaces(List<Place> places) {
        return (root, query, cb) -> {
//            Join<Character, Place> places = root.join("livePlace");
            return root.get("livePlace").in(places);
        };
    }

    public static Specification<Character> getByReligions(List<Religion> religions) {
        return (root, query, cb) -> {
            return root.get("religion").in(religions);
        };
    }

    public static Specification<Character> getByBirthPlaces(List<Place> birthPlaces) {
        return (root, query, cb) -> {
            return root.get("birthPlace").in(birthPlaces);
        };
    }

    public static Specification<Character> getByStarSigns(List<StarSign> starSigns) {
        return (root, query, cb) -> {
            return root.get("starSign").in(starSigns);
        };
    }
}