package de.dueto.backend.service;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(initializers = {de.dueto.backend.service.UserServiceTest.Initializer.class})
class UserServiceTest {

    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0")
            .withDatabaseName("DueTo-Database")
            .withUsername("DueTo")
            .withPassword("password");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            mySQLContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mySQLContainer.getUsername(),
                    "spring.datasource.password=" + mySQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    List<Group> groups = new ArrayList<>();
    User user = new User(1,"max","test@email.de","1234pass",null, groups);

    @Test
    void findByUsername() {

        userService.save(user);
        assertEquals(user, userService.findByUsername("max"));

    }

}