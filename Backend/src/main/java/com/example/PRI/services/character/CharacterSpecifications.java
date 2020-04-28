package com.example.PRI.services.character;

import com.example.PRI.entities.character.Name;
import com.example.PRI.entities.character.Surname;
import com.example.PRI.entities.character.Talent;
import org.springframework.data.jpa.domain.Specification;
import com.example.PRI.entities.character.Character;

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

    public static Specification<Character> getByTalent(Talent talent) { //DZIAŁA!!
        return (root, query, cb) -> {
            Join<Character, Talent> talents = root.join("talents");
            return cb.equal(talents.get("name"), talent.getName());
            //ToDo identyczne zapytania dla innych cech postaci, które mogą być filtrowane przez obiekt
        };
    }

    public static Specification<Character> getByTalents(List<Talent> talentList){
        return (root, query, cb) -> {
            Join<Character, Talent> talents = root.join("talents");
            return talents.in(talentList); // DZIAŁA!!
            }; //ToDo identyczne zapytania dla innych cech postaci, które mogą być filtrowane przez listę.
    }
}