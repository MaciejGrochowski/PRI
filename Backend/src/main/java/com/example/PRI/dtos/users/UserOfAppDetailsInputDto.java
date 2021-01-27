package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppDetailsInputDto implements Serializable {

    @Pattern(regexp = "^.{0,1000}$", message = "WRONG_DESCRYPTION_LENGTH")
    String description;

//    @NotBlank(message = "NO_EMAIL")
//    String mail;

    @Pattern(regexp = "^.[^#@:`']{1,31}#\\d\\d\\d\\d$",message = "WRONG_DISCORD_NAME")
    @Size(max=10000)
    String discord;

    @Pattern(regexp = "^(https:\\/\\/www.facebook.com\\/.*)$|^$", message = "WRONG_FACEBOOK_LINK")
    @Size(max=10000)
    String facebook;

}
