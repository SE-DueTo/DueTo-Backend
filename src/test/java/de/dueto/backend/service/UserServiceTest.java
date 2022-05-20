package de.dueto.backend.service;

import de.dueto.backend.DatabaseTest;
import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.shaded.org.bouncycastle.asn1.dvcs.Data;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest extends DatabaseTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService = new UserService(userRepository);

    List<Group> groups = new ArrayList<>();
    User user = new User(1,"max","test@email.de","1234pass",null, groups);

    @Test
    void findByUsername() {

        userService.save(user);
        assertEquals(user, userService.findByUsername("max"));

    }

}