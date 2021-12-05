package de.dueto.backend.controller.v1;

import de.dueto.backend.model.registration.RegistrationResponseDTO;
import de.dueto.backend.model.user.RegistrationUserDTO;
import de.dueto.backend.model.user.User;
import de.dueto.backend.model.user.UserMapper;
import de.dueto.backend.security.Session;
import de.dueto.backend.security.UserValidator;
import de.dueto.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("v1/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/register")
    public RegistrationResponseDTO registration(@RequestBody RegistrationUserDTO userDTO, BindingResult bindingResult, BCryptPasswordEncoder passwordEncoder) {
        userValidator.validate(userDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return RegistrationResponseDTO.builder()
                    .successful(false)
                    .errorMessage(bindingResult.getAllErrors()) //TODO
                    .build();
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.save(UserMapper.fromRegistrationUserDTO(userDTO));

        return RegistrationResponseDTO.builder()
                .successful(true)
                .build();
    }

    @PostMapping("/logout")
    public boolean logout(HttpSession session) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        if(user == null) return false;

        session.invalidate();

        return true;
    }
}
