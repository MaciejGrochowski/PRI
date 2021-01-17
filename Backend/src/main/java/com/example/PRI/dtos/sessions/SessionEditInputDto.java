package com.example.PRI.dtos.sessions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionEditInputDto {
    String hashcode;
    @NotBlank(message = "NO_SESSION_NAME")
    String name;
    String description;
}
