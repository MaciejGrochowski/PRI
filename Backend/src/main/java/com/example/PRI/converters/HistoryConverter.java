package com.example.PRI.converters;

import com.example.PRI.dtos.histories.HistoryOutputDto;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.User;
import com.example.PRI.entities.history.History;

import java.util.Date;

public class HistoryConverter {


    public static HistoryOutputDto convert(History c) {
        if(c.getCreatedBy() == null) c.setCreatedBy(new User());
        if(c.getPlace() == null) c.setPlace(new Place()); //ToDo Delete this after tests - all histories must have creator and place
        return new HistoryOutputDto(c.getId(), c.getDate().getDay().toString(), c.getDate().getMonth().getMonthName(),
                c.getDate().getYear().toString(), c.getPlace().getName(), c.getCreatedBy().getUsername(), c.getCreatedDate());

    }
}
