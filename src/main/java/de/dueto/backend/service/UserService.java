package de.dueto.backend.service;

import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findFirstByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    public List<User> findByUsernameContaining(String username, long limit) {
        return userRepository.findByUsernameContaining(username).stream().limit(limit).toList();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
