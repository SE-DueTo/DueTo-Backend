package de.dueto.backend.model.settle_debt;

import de.dueto.backend.DatabaseMock;
import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SettleDebtMapperTest extends DatabaseMock {

    @Autowired
    SettleDebtMapper settleDebtMapper1;

    SettleDebtMapper settleDebtMapper;

    List<Group> groups = new ArrayList<>();
    Group group =  new Group();
    Date date = new Date();
    User debtor = new User(5,"max","test1@email.de","1234pass",null, groups);
    User creditor = new User(29,"maxxi","test2@email.de","123456",null, groups);

    @Test
    void fromSettleDebtNullPointerException() {

        SettleDebt settleDebt =  new SettleDebt();

        assertThrows(NullPointerException.class, ()->
            settleDebtMapper.fromSettleDebt(settleDebt)
        );
    }

    @Test
    void fromSettleDebt() {

        SettleDebt settleDebt =  new SettleDebt(5, 2000, group, debtor, 5, creditor, 27, "paypal", date);
        SettleDebtDTO settleDebtDTO = new SettleDebtDTO(5, 0, 2000, debtor, 5, creditor,27, "paypal", date);

        assertEquals(settleDebtDTO, settleDebtMapper1.fromSettleDebt(settleDebt));
    }

    @Test
    void fromSettleDebtAddDTONullPointerException() {

        SettleDebtAddDTO addDTO = new SettleDebtAddDTO();

        assertThrows(NullPointerException.class, ()->
            settleDebtMapper.fromSettleDebtAddDTO(addDTO)
        );
    }

}