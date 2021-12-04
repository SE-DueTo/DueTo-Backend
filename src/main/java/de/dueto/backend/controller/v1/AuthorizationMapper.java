package de.dueto.backend.controller.v1;

import de.dueto.backend.model.user.User;
import de.dueto.backend.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationMapper {

    private final UserService userService;

    public AuthorizationMapper(UserService userService) {
        this.userService = userService;
    }

    public User getUser(String token) {
        String username = Jwts.parser().setSigningKey("SecretKeyToGenJWTs".getBytes())
                .parseClaimsJws(token.replace("Bearer",""))
                .getBody()
                .getSubject();

        return userService.findByUsername(username);
    }

}
