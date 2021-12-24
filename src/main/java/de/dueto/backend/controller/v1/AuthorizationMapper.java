package de.dueto.backend.controller.v1;

import de.dueto.backend.model.user.User;
import de.dueto.backend.security.secret.JwtSecret;
import de.dueto.backend.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationMapper {

    private final UserService userService;
    private final String jwtSecret;

    public AuthorizationMapper(UserService userService, JwtSecret jwtSecret) {
        this.userService = userService;
        this.jwtSecret = jwtSecret.getJtwSecret();
    }

    public User getUser(String token) {
        String username = Jwts.parser().setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token.replace("Bearer",""))
                .getBody()
                .getSubject();

        return userService.findByUsername(username);
    }

}
