package de.dueto.backend.service;

import de.dueto.backend.mysqlData.SettleDebtRepository;
import org.springframework.stereotype.Service;

@Service
public class SettleDebtService {

    private final SettleDebtRepository settleDebtRepository;

    public SettleDebtService(SettleDebtRepository settleDebtRepository) {
        this.settleDebtRepository = settleDebtRepository;
    }
}
