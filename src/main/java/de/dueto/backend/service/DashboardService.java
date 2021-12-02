package de.dueto.backend.service;

import de.dueto.backend.mysqlData.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UserRepository userRepository;

    public DashboardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
