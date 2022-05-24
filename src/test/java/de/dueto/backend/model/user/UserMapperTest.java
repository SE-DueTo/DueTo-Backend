package de.dueto.backend.model.user;

import de.dueto.backend.DatabaseTest;
import de.dueto.backend.model.group.Group;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest extends DatabaseTest {

    private List<Group> groups = new ArrayList<>();
    User user = new User(0,"max","test@email.de","1234pass",null, groups);

    @Test
    void fromRegistrationUserDTO() {

        RegistrationUserDTO registrationUserDTO = new RegistrationUserDTO("max","1234pass", "test@email.de");

        assertEquals(user, UserMapper.fromRegistrationUserDTO(registrationUserDTO));
    }
}