package de.dueto.backend.service;

import de.dueto.backend.model.user.User;
import de.dueto.backend.mysqlData.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
