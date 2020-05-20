package com.example.PRI.services;

import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.character.Name;
import com.example.PRI.repositories.ImperialDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImperialDateService extends GeneralService {

    @Autowired
    ImperialDateRepository imperialDateRepository;

    public ImperialDate save(ImperialDate imperialDate) {
        ImperialDate imperialDateNew = imperialDateRepository.findByYearAndMonthAndDay(imperialDate.getYear(),imperialDate.getMonth(),imperialDate.getDay());
        if (imperialDateNew != null) imperialDate.setId(imperialDateNew.getId());
        imperialDateRepository.save(imperialDate);
        return imperialDate;
    }


}
