package de.dueto.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dueto.backend.model.user.SimpleUserDTO;
import de.dueto.backend.security.secret.JwtSecret;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.annotations.Filter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Filter(name = "AuthenticationFilter")
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    static class AuthenticationAttemptException extends AuthenticationException {
        public AuthenticationAttemptException(String msg) {
            super(msg);
        }
    }

    private final AuthenticationManager authenticationManager;

    private final String jwtSecret;

    public AuthenticationFilter(AuthenticationManager authenticationManager, JwtSecret jwtSecret) {
        this.authenticationManager = authenticationManager;
        this.jwtSecret = jwtSecret.getJtwSecret();
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            SimpleUserDTO creds = new ObjectMapper().readValue(request.getInputStream(), SimpleUserDTO.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(),new ArrayList<>())
            );
        } catch(IOException e) {
            throw new AuthenticationAttemptException("Could not read request" + e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication authentication) {



        String token = Jwts.builder()
            .setSubject(((User) authentication.getPrincipal()).getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
            .compact();
        response.addHeader("Authorization","Bearer " + token);
    }
}