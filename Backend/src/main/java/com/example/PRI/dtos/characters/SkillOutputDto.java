package com.example.PRI.dtos.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillOutputDto {
    private String name;
    private Integer level;
}
