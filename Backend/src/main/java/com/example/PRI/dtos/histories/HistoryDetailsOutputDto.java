package com.example.PRI.dtos.histories;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDetailsOutputDto {

    String day;
    String month;
    String year;
    String place;
    String creator;
    String description;
    String title;


}
