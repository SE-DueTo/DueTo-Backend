package de.dueto.backend.model.settle_debt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SettleDebtMapperTest {

    @Autowired
    SettleDebtMapper settleDebtMapper;

    @Test
    void fromSettleDebtAddDTO() {

        SettleDebtAddDTO addDTO = null;

        assertThrows(NullPointerException.class, ()->{
            settleDebtMapper.fromSettleDebtAddDTO(addDTO);
        });

        assertTrue(true);
    }

    @Test
    void fromSettleDebt() {
    }
}