package com.example.PRI.dtos.sessions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharactersSessionInputDto {

    Long selectedSessionId;
    List<Long> characterIds;

}
