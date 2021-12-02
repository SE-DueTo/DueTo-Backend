package de.dueto.backend.controller.v1;

import de.dueto.backend.service.UserService;
import org.springframework.stereotype.Controller;

@Controller("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
