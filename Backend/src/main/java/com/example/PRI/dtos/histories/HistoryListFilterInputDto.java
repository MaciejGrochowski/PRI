package com.example.PRI.dtos.histories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryListFilterInputDto {
    private String sortedBy;
    private Boolean isAscending;
    private Map<String, String> filters;
    private Integer currentPage;
    private Integer rowsPerPage;


}
