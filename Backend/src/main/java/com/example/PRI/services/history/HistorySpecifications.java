package com.example.PRI.services.history;

import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.history.History;
import com.example.PRI.enums.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

public class HistorySpecifications {
    public static Specification<History> getByName(Name name) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    public static Specification<Character> getBySurname(Surname surname) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("surname"), surname);
        };
    }

    public static Specification<History> getAll() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNotNull(root.get("id")); //ToDo lepszy warunek niż nullowe imię
        };
    }

    public static Specification<History> GetNoone() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNull(root.get("id")); //ToDo lepszy warunek niż nullowe imię
        };
    }

    public static Specification<History> getByHistoryDay(int day) {
        return (root, query, criteriaBuilder) -> {
            Join<Character, ImperialDate> date = root.join("date");
            return criteriaBuilder.equal(date.get("day"), day);
        };
    }

    public static Specification<History> getByHistoryYear(int year) {
        return (root, query, criteriaBuilder) -> {
            Join<Character, ImperialDate> date = root.join("date");
            return criteriaBuilder.equal(date.get("year"), year);
        };
    }


    public static Specification<History> getByMonthsIn(List<Month> months) {
        return (root, query, criteriaBuilder) -> {
            Join<Character, ImperialDate> date = root.join("date");
            return criteriaBuilder.isTrue(date.get("month").in(months));
        };
    }

    public static Specification<History> getByPlaces(List<Place> places) {
        return (root, query, criteriaBuilder) -> {
            Join<Character, Place> placesJoin = root.join("place");
            return criteriaBuilder.isTrue(placesJoin.get("name").in(places.stream().map(Place::getName).collect(Collectors.toList())));
        };
    }

    public static Specification<History> getByCharacterInDescription(String historyCharactersInDescription) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    root.<String>get("description"), "%" + historyCharactersInDescription + "%");
//            return query.where(criteriaBuilder.like(root.get("description"), "%" + historyCharactersInDescription)).getRestriction();
//            return criteriaBuilder.like(root.get("description"), historyCharactersInDescription);
        };
    }

    public static Specification<History> getByHistoryTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.<String>get("title"), "%" + title + "%");
    }

    public static Specification<History> getByCreator(List<UserOfApp> users) {
        return (root, query, cb) -> {
//            query.distinct(true);
            Join<Character, UserOfApp> usersTab = root.join("createdBy");
            return usersTab.in(users);
        };
    }
}