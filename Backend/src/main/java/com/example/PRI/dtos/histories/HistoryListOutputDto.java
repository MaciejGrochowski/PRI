package com.example.PRI.dtos.histories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryListOutputDto {
    private long totalCount;
    private List<HistoryOutputDto> list;
}
