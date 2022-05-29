package de.dueto.backend.model.transaction;

import de.dueto.backend.DatabaseMock;
import de.dueto.backend.model.group.Group;
import de.dueto.backend.mysql_data.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionMapperTest extends DatabaseMock {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TransactionMapper transactionMapper = new TransactionMapper(groupRepository);

    TransactionMapper transactionMapper1;

    Group group = new Group();
    Date date = new Date();
    HashMap<Long, Long> userAmountList;
    Transaction transaction = new Transaction(10, group, 23, "trip", "paypal", date, userAmountList, null);

    @Test
    void fromTransactionNullPointerException() {

        Transaction transaction = new Transaction();

        assertThrows(NullPointerException.class, ()->
            transactionMapper1.fromTransaction(transaction)
        );

        assertTrue(true);

    }

    @Test
    void fromTransaction() {

        TransactionDTO transactionDTO = new TransactionDTO(10, group, 23, "trip", "paypal", date, userAmountList, null);

        assertEquals(transactionDTO, transactionMapper.fromTransaction(transaction));

    }

    @Test
    void fromTransactionAddDTONullPointerException() {

        TransactionAddDTO transactionAddDTO = new TransactionAddDTO();

        assertThrows(NullPointerException.class, ()->
            transactionMapper1.fromTransactionAddDTO(transactionAddDTO)
        );

    }

}