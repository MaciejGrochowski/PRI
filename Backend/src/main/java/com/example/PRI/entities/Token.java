package com.example.PRI.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name ="tokens")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token extends GeneralEntity{

    @ManyToOne
    @JoinColumn(name="token_user", nullable=false)
    UserOfApp tokenUser;

    @Column(unique = true)
    String name;
}
