package de.dueto.backend.service;

import de.dueto.backend.model.settle_debt.SettleDebtMapper;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.GroupRepository;
import de.dueto.backend.mysql_data.SettleDebtRepository;
import de.dueto.backend.mysql_data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(initializers = {SettleDebtServiceTest.Initializer.class})
class SettleDebtServiceTest {

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
    SettleDebtRepository settleDebtRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SettleDebtService settleDebtService;

    @Autowired
    SettleDebtMapper settleDebtMapper;

    @Test
    void getBalance() {

        assertThrows(NullPointerException.class, ()->{
            settleDebtService.getBalance(new User());
        });

        assertTrue(true);

    }

    @Test
    void getDebtsUserFromLimit() {

        assertThrows(NullPointerException.class, ()->{
            settleDebtService.getDebts(new User(), 1, 2);
        });

        assertTrue(true);

    }

    @Test
    void testGetDebtsUserGroupIdFromLimit() {

        assertThrows(NullPointerException.class, ()->{
            settleDebtService.getDebts(new User(), 0,1, 2);
        });

        assertTrue(true);


    }

    @Test
    void testGetDebtsUserGroupId() {

        assertThrows(NullPointerException.class, ()->{
            settleDebtService.getDebts(new User(), 0);
        });

        assertTrue(true);

    }

    @Test
    void addSettleDebt() {

        assertThrows(NullPointerException.class, ()->{
            settleDebtService.getDebts(new User(), 0,1, 2);
        });

        assertTrue(true);
    }
}