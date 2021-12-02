package de.dueto.backend.security;

import de.dueto.backend.model.user.User;
import de.dueto.backend.mysqlData.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger("details");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug(username);
        User user = userRepository.findUserByUsername(username);
        logger.debug(Objects.toString(user));
        if(user == null) throw new UsernameNotFoundException("User not found (" + username + ")");

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>()
        );
    }
}
