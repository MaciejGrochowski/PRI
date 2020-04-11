package com.example.PRI.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name ="chomik")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chomik extends GeneralEntity {

    @Column(unique = true)
    String name;

    Integer number_of_legs;

    boolean is_happy;
    boolean is_hungry;

    @OneToOne
    Chomik love_interest;

    public  void sayHello(){
        System.out.println("Hello, my name is " + this.name);
    }
}
