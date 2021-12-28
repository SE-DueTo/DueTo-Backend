package de.dueto.backend.controller.v1;

import de.dueto.backend.model.registration.RegistrationResponseDTO;
import de.dueto.backend.model.user.RegistrationUserDTO;
import de.dueto.backend.model.user.User;
import de.dueto.backend.model.user.UserMapper;
import de.dueto.backend.model.Session;
import de.dueto.backend.security.UserValidator;
import de.dueto.backend.service.SessionService;
import de.dueto.backend.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("v1/user/")
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;
    private final UserValidator userValidator;

    public UserController(UserService userService, SessionService sessionService, UserValidator userValidator) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.userValidator = userValidator;
    }



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
    public boolean logout(HttpSession session, @RequestHeader(value="Authorization") String token) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        if(user == null) return false;

        session.invalidate();

        String sessionId = token.replace("Bearer", "").trim();
        sessionService.destroySession(sessionId);

        return true;
    }
}
