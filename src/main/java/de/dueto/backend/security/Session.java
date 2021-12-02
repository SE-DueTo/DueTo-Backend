package de.dueto.backend.security;

import de.dueto.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    public final static String USER_TOKEN = "user_token";

    private User user;

}
