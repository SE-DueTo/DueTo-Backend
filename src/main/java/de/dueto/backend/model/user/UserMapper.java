package de.dueto.backend.model.user;


import java.util.ArrayList;

public class UserMapper {

    private UserMapper() {}
    public static User fromRegistrationUserDTO(RegistrationUserDTO registrationUserDTO) {
        return new User.UserBuilder()
                .username(registrationUserDTO.getUsername())
                .email(registrationUserDTO.getEmail())
                .password(registrationUserDTO.getPassword())
                .avatarUrl(null)
                .groups(new ArrayList<>())
                .build();
    }
}
