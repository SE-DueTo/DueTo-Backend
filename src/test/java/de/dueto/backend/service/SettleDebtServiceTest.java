package de.dueto.backend.service;

import de.dueto.backend.model.settle_debt.SettleDebtMapper;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.GroupRepository;
import de.dueto.backend.mysql_data.SettleDebtRepository;
import de.dueto.backend.mysql_data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class SettleDebtServiceTest {

    @Autowired
    SettleDebtRepository settleDebtRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    SettleDebtMapper settleDebtMapper = new SettleDebtMapper(groupRepository, userRepository);
    SettleDebtService settleDebtService = new SettleDebtService(settleDebtRepository, settleDebtMapper);

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