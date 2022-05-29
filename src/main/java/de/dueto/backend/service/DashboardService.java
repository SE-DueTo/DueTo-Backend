package de.dueto.backend.service;

import de.dueto.backend.model.dashboard.DashboardDataDTO;
import de.dueto.backend.model.user.User;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final TransactionService transactionService;
    private final SettleDebtService settleDebtService;


    public DashboardService(
            TransactionService transactionService,
            SettleDebtService settleDebtService) {
        this.transactionService = transactionService;
        this.settleDebtService = settleDebtService;
    }

    public DashboardDataDTO getData(@NonNull User user) {
        return DashboardDataDTO.builder()
                .user(user)
                .groups(user.getGroups())
                .balance(getBalance(user))
                .build();
    }
    private long getBalance(@NonNull User user) {
        return settleDebtService.getBalance(user) - transactionService.getBalance(user);
    }
}
