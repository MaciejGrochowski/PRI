package com.example.PRI.dtos.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterListFilterInputDto {

    @Size(max=10000)
    private String sortedBy;
    private Boolean isAscending;
    private Map<String, String> filters;
    private Integer currentPage;
    private Integer rowsPerPage;


}
