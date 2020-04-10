package com.example.PRI.entities.session;

import com.example.PRI.entities.GeneralEntity;
import com.example.PRI.entities.character.Character;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name ="session_characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionCharacter extends GeneralEntity {

    @ManyToOne
    Character character;

    @ManyToOne
    AttributesVisibility attributesVisibility;

}
