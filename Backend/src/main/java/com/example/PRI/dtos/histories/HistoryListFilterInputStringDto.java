package com.example.PRI.dtos.histories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryListFilterInputStringDto {
    @Size(max=10000)
    private String sortedBy;
    private Boolean isAscending;
    @Size(max=10000)
    private String filters;
    private Integer currentPage;
    private Integer rowsPerPage;


}
