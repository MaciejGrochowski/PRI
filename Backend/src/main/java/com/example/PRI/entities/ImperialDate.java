package com.example.PRI.entities;

import com.example.PRI.enums.Month;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name ="dates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImperialDate extends GeneralEntity {

    int day; //uwaga - liczba dni w miesiącu jest różna dla różnych miesięcy, niezbędna walidacja
    Month month;
    short year;


}
