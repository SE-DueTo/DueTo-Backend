package de.dueto.backend.security;

import de.dueto.backend.model.user.RegistrationUserDTO;
import de.dueto.backend.model.user.SimpleUserDTO;
import de.dueto.backend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SimpleUserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationUserDTO user = (RegistrationUserDTO) o;

        String usernameField = "username";

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, usernameField, "NotEmpty");
        if (user.getUsername().length() < 2 || user.getUsername().length() > 32) {
            errors.rejectValue(usernameField, "Size.userForm.username");
        }
        if (userService.findFirstByUsername(user.getUsername()) != null) {
            errors.rejectValue(usernameField, "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}
