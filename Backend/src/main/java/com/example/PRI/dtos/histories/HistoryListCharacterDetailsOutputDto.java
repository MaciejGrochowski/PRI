package com.example.PRI.dtos.histories;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HistoryListCharacterDetailsOutputDto {
    String beginDescription;
    String title;
    Long id;
}
