package de.dueto.backend.service;

import de.dueto.backend.model.settleDebt.SettleDebt;
import de.dueto.backend.model.transaction.Transaction;
import de.dueto.backend.model.dashboard.DashboardDataDTO;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysqlData.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final TransactionService transactionService;
    private final SettleDebtService settleDebtService;

    public DashboardService(UserRepository userRepository, TransactionService transactionService, SettleDebtService settleDebtService) {
        this.userRepository = userRepository;
        this.transactionService = transactionService;
        this.settleDebtService = settleDebtService;
    }

    public DashboardDataDTO getData(User user) {
        return DashboardDataDTO.builder()
                .user(user)
                .groups(user.getGroups())
                .balance(getBalance(user))
                .build();
    }
    private long getBalance(User user) {
        return settleDebtService.getBalance(user) - transactionService.getBalance(user);
    }

    public List<Transaction> getTransactions(User user, long groupId, long from, long limit) {
        return transactionService.getTransactions(user,groupId, from, limit);
    }

    public List<Transaction> getTransactions(User user, long from, long limit) {
        return transactionService.getTransactions(user, from, limit);
    }

    public List<SettleDebt> getDebts(User user, long from, long limit) {
        return settleDebtService.getTransactions(user, from, limit);
    }
}
