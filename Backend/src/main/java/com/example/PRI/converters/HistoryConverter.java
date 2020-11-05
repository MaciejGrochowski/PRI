package com.example.PRI.converters;

import com.example.PRI.dtos.histories.HistoryDetailsOutputDto;
import com.example.PRI.dtos.histories.HistoryOutputDto;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.User;
import com.example.PRI.entities.history.History;

import java.util.Date;

public class HistoryConverter {


    public static HistoryOutputDto convert(History c) {
        if(c.getCreatedBy() == null) c.setCreatedBy(new User());
         //ToDo Delete this after users - all histories must have creator
        return new HistoryOutputDto(c.getId(), c.getDate().getDay().toString(), c.getDate().getMonth().getMonthName(),
                c.getDate().getYear().toString(), c.getPlace().getName(), c.getCreatedBy().getUsername(), c.getCreatedDate());

    }

    public static HistoryDetailsOutputDto convertDetails(History h){
        HistoryDetailsOutputDto output = new HistoryDetailsOutputDto();
        output.setDay(h.getDate().getDay().toString());
        output.setMonth(h.getDate().getMonth().getMonthName());
        output.setYear(h.getDate().getYear().toString());
        output.setCreator(null); //Todo createdBy here when users ready
        output.setPlace(h.getPlace().getName());
        output.setDescription(h.getDescription());
        return output;
    }
}
