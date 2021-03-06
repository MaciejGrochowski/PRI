package com.example.PRI.dtos.sessions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionInputDto {

    @NotBlank(message = "NO_SESSION_NAME")
    @Size(max=128, message = "TOO_LONG_SESSION_NAME")
    String name;

    @Size(max=100000)
    String description;

}
