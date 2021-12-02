package de.dueto.backend.service;

import de.dueto.backend.model.user.User;
import de.dueto.backend.mysqlData.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final UserRepository userRepository;

    public MainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User u) {
        userRepository.save(u);
    }

    public Iterable<User> getALl() {
        return userRepository.findAll();
    }

}
