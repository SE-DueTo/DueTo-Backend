package de.dueto.backend.security.secret;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JwtSecret {

    @Value("${secrets.jwt-secret}")
    private String jtwSecret;

}
