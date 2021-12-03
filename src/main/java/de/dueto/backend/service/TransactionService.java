package de.dueto.backend.service;

import de.dueto.backend.model.transaction.Transaction;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysqlData.TransactionRepository;
import de.dueto.backend.mysqlData.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public long getBalance(User user) {
        return transactionRepository.getAllByUserId(user.getUserId())
                .stream()
                .mapToLong(transaction -> transaction.getUserAmountList().get(user.getUserId()))
                .sum();
    }

    public List<Transaction> getTransactions(User user, long groupId, long from, long limit) {
        return transactionRepository.getAllByUserId(user.getUserId())
                .stream()
                .skip(from)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactions(User user, long groupId) {
        return transactionRepository.getAllByUserId(user.getUserId()); //ToDo return users with group id
    }

    public List<Transaction> getTransactions(User user, long from, long limit) {
        return transactionRepository.getAllByUserId(user.getUserId());
    }
}