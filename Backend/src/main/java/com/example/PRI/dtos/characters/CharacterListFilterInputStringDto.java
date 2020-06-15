package com.example.PRI.dtos.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterListFilterInputStringDto {
    private String sortedBy;
    private Boolean isAscending;
    private String filters;
    private Integer currentPage;
    private Integer rowsPerPage;


}
