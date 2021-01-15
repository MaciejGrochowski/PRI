package com.example.PRI.dtos.sessions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionEditInputDto {
    String hashcode;
    String name;
    String description;
}