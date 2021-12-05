package de.dueto.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dueto.backend.model.user.SimpleUserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${secrets.JWT_SECRET}")
    private String jwtSecret;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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