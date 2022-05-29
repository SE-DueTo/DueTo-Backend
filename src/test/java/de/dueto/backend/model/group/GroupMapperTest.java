package de.dueto.backend.model.group;

import de.dueto.backend.DatabaseMockUtils;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupMapperTest extends DatabaseMockUtils {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupMapper groupMapper = new GroupMapper(userRepository);

    GroupMapper groupMapper1;

    List<Long> users= new ArrayList<>();
    List<User> userList = new ArrayList<>();

    Group group1 = new Group(0, "Normal", "1234pass", GroupType.NORMAL, userList);

    @Test
    void fromGroupAddNormalDTO() {

        GroupAddNormalDTO groupAddNormalDTO = new GroupAddNormalDTO("Normal", users,"1234pass");

        assertEquals(group1, groupMapper.fromGroupAddNormalDTO(groupAddNormalDTO));

    }

    @Test
    void fromGroupAddSpontaneous() {

        User user1 = new User(0,"max","test1@email.de","1234pass",null, null);

        assertThrows(NullPointerException.class, ()->
            groupMapper1.fromGroupAddSpontaneous(user1,0)
        );
    }

}