package com.example.PRI.dtos.sessions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionOutputDto {

    String id;
    String name;
    String description;
    Date lastModifiedDate;
    Date createdDate;
    String createdUserBy;
    String hashcode;



}
