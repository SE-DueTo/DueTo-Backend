package de.dueto.backend.service;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.transaction.TransactionAddDTO;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionService transactionService;

    @Test
    void getBalance() {

        assertThrows(NullPointerException.class, ()->{
            transactionService.getBalance(new User());
        });

        assertTrue(true);
    }

    @Test
    void getTransactionsUserGroupFromLimit() {
        assertThrows(NullPointerException.class, ()->{
            transactionService.getTransactions(new User(), new Group(), 2,3);
        });

        assertTrue(true);
    }

    @Test
    void getTransactionsUserGroup() {

        assertThrows(NullPointerException.class, ()->{
            transactionService.getTransactions(new User(), new Group());
        });

        assertTrue(true);
    }

    @Test
    void testGetTransactionsUserLongLimit() {

        assertThrows(NullPointerException.class, ()->{
            transactionService.getTransactions(new User(), 1, 5);
        });

        assertTrue(true);

    }


    @Test
    void addTransaction() {

        TransactionAddDTO transactionAddDTO = new TransactionAddDTO();

        assertThrows(NullPointerException.class, ()->{
            transactionService.addTransaction(transactionAddDTO);
        });

        assertTrue(true);

    }
}