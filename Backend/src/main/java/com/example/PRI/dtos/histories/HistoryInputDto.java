package com.example.PRI.dtos.histories;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryInputDto {

    @NotBlank(message = "NO_HISTORY_DAY")
    @Pattern(regexp = "^[1-9]$|^[1-2][0-9]$|^3[0-4]$", message = "DAY_OF_HISTORY_NOT_REGEXP")
    String day;

    @NotBlank(message = "NO_MONTH_OF_HISTORY")
    @Pattern(regexp ="Nachexen|Jahrdrung|Pflugzeit|Sigmarzeit|Sommerzeit|Vorgeheim|Nachgeheim|Erntezeit|Brauzeit|Kaldezeit|Ulriczeit|Vorhexen",
            message = "MONTH_OF_HISTORY_NOT_REGEXP")
    String month;

    @NotBlank(message = "NO_YEAR_OF_HISTORY")
    @Pattern(regexp = "^[1-9]$|^[1-9][0-9]$|^[1-9][0-9][0-9]$|^[1-2][0-9][0-9][0-9]$|^3000$", message = "YEAR_OF_HISTORY_NOT_REGEXP")
    String year;

    @NotBlank(message = "NO_HISTORY_PLACE")
    String place;

//    @NotBlank(message = "NO_HISTORY_DESCRIPTION")
    @Length(min = 9, message = "NO_HISTORY_DESCRIPTION")
    String description;

    @Length(min = 1, max = 256)
    String title;
}
