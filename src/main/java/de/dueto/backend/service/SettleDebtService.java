package de.dueto.backend.service;

import de.dueto.backend.model.SettleDebt;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysqlData.SettleDebtRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettleDebtService {

    private final SettleDebtRepository settleDebtRepository;

    public SettleDebtService(SettleDebtRepository settleDebtRepository) {
        this.settleDebtRepository = settleDebtRepository;
    }

    public long getBalance(User user) {
        return settleDebtRepository.getAllByDebtorEquals(user)
                .stream()
                .mapToLong(SettleDebt::getAmount)
                .sum();
    }

    public List<SettleDebt> getTransactions(User user, long from, long limit) {
        return settleDebtRepository.getAllByDebtorEquals(user)
                .stream()
                .skip(from)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
