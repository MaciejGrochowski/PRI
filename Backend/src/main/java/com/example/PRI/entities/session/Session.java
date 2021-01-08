package com.example.PRI.entities.session;


import com.example.PRI.entities.GeneralEntity;
import com.example.PRI.entities.UserOfApp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name="created_by", nullable=false)
    UserOfApp createdUserOfApp;
    Date createdDate;
    Date lastModifiedDate;
    String name;

    @Type(type="text")
    String description;
    String randomIdSession; //id dla udostępniania sesji

//    @ManyToOne
//    UserOfApp userOfAppMg;

    @ManyToOne
    AttributesVisibility globalAttiributes;

    @ManyToMany
    List<SessionCharacter> sessionCharacterList;





}
