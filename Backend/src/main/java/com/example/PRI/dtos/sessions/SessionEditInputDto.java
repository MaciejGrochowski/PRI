package com.example.PRI.dtos.sessions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionEditInputDto {
    @Size(max=10000)
    String hashcode;
    @NotBlank(message = "NO_SESSION_NAME")
    @Size(max=10000)
    String name;
    @Size(max=10000)
    String description;
}
