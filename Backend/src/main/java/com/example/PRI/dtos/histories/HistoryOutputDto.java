package com.example.PRI.dtos.histories;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryOutputDto {

    Long id;
    String historyDay;
    String historyMonth;
    String historyYear;
    String historyPlace;
    String createdBy;
    Date createdTime;

}
