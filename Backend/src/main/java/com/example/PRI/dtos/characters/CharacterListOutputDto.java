package com.example.PRI.dtos.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterListOutputDto {
    private long totalCount;
    private List<CharacterDefaultAttributesOutputDto> list;
}
