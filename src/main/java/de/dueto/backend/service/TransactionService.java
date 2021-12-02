package de.dueto.backend.service;

import de.dueto.backend.model.Transaction;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysqlData.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public long getBalance(User user) {
        return transactionRepository.getAllByUserId(user.getUserId())
                .stream()
                .mapToLong(transaction -> transaction.getUserAmountList().get(user.getUserId()))
                .sum();
    }

    public List<Transaction> getTransactions(User user, long from, long limit) {
        return transactionRepository.getAllByUserId(user.getUserId())
                .stream()
                .skip(from)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
