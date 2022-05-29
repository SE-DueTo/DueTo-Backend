package de.dueto.backend;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DirtiesContext
public class DatabaseMock {

    @Container
    @SuppressWarnings("rawtypes")
    private static final MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0")
            .withDatabaseName("DueTo-Database")
            .withUsername("DueTo")
            .withPassword("password");

    static {
        mySQLContainer.start();
    }

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("sprin.datasource.hikari.connection-timeout", () -> 250);
    }

}
