package com.example.PRI.dtos.users;

import com.example.PRI.dtos.characters.UserOfAppCharacterOutputDto;
import com.example.PRI.dtos.histories.HistoryListCharacterDetailsOutputDto;
import com.example.PRI.entities.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppInputDto implements Serializable {

    String description;

    @NotBlank(message = "NO_EMAIL")
    String mail;
    String discord;
    String facebook;

}
