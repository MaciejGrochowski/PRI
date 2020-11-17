package com.example.PRI.dtos.characters;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterTagOutputDto {
    String text;
    String value;
    String url;
}
