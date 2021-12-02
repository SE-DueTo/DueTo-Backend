package de.dueto.backend.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationUserDTO {

    private String username;
    private String password;
    private String email;

}
