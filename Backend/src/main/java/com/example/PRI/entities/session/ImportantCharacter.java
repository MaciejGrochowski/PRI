package com.example.PRI.entities.session;

import com.example.PRI.entities.GeneralEntity;
import com.example.PRI.entities.User;
import com.example.PRI.entities.character.Character;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity(name ="important_characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportantCharacter extends GeneralEntity {

    @ManyToOne
    User user;

    @ManyToOne
    Session session;

    @ManyToMany
    List<Character> characters;
}
