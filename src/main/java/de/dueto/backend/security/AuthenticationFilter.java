package de.dueto.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dueto.backend.model.Session;
import de.dueto.backend.model.user.SimpleUserDTO;
import de.dueto.backend.service.SessionService;
import de.dueto.backend.service.UserService;
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

@Filter(name = "AuthenticationFilter")
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    static class AuthenticationAttemptException extends AuthenticationException {
        public AuthenticationAttemptException(String msg) {
            super(msg);
        }
    }

    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final SessionService sessionService;

    public AuthenticationFilter(AuthenticationManager authenticationManager, SessionService sessionService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.sessionService = sessionService;
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
            Authentication authentication) throws IOException {

        String username = ((User) authentication.getPrincipal()).getUsername();
        de.dueto.backend.model.user.User user = userService.findByUsername(username);

        if(user == null) return;

        Session session = new Session();
        session.setUser(user);

        sessionService.save(session);

        String token = "Bearer " + session.getSessionId();
        response.addHeader("Authorization", token);
        response.getOutputStream().print(token);
    }
}