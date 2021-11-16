package de.dueto.backend.service;

import de.dueto.backend.controller.MainController;
import de.dueto.backend.model.User;
import de.dueto.backend.mysqlData.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User u) {
        userRepository.save(u);
    }

    public Iterable<User> getALl() {
        return userRepository.findAll();
    }

}
