package de.dueto.backend.controller.v1;

import de.dueto.backend.model.user.User;
import de.dueto.backend.service.SessionService;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationMapper {

    private final SessionService sessionService;

    public AuthorizationMapper(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public User getUser(String t) {
        String token = t.replace("Bearer","").trim();
        return sessionService.getUser(token);
    }

}
