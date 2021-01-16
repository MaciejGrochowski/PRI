package com.example.PRI.dtos.sessions;


import com.example.PRI.dtos.characters.CharacterSessionOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDetailsOutputDto {

    String name;
    String description;
    String createdBy;
    Date createdDate;
    List<CharacterSessionOutputDto> characters;


}
