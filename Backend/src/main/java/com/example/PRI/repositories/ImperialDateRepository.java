package com.example.PRI.repositories;

import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.enums.Month;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImperialDateRepository extends CrudRepository<ImperialDate, Long> {

    ImperialDate findByYearAndMonthAndDay(int year, Month month, int day);
}
