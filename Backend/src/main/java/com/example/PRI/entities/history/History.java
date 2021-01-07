package com.example.PRI.entities.history;

import com.example.PRI.entities.GeneralEntity;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.entities.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name ="histories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History extends GeneralEntity {

    @ManyToOne
    ImperialDate date;

    @ManyToOne
    @JoinColumn(name="created_by", nullable=false)
    UserOfApp createdBy; //Potem to bedzie uzytkownik

    Date createdDate;

    @Type(type="text")
    String description;

    String title;

    @ManyToOne
    Place place;

}
