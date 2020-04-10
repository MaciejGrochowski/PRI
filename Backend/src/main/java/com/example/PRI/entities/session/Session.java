package com.example.PRI.entities.session;


import com.example.PRI.entities.GeneralEntity;
import com.example.PRI.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity(name ="sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session extends GeneralEntity {

    @ManyToOne
    User createdUser;
    Date createdDate;
    Date lastModifiedDate;
    String name;
    String description;
    String randomIdSession; //id dla udostępniania sesji

    @ManyToOne
    User userMg;

    @ManyToOne
    AttributesVisibility globalAttiributes;

    @ManyToMany
    List<SessionCharacter> sessionCharacterList;





}
