package de.dueto.backend.service;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.transaction.Transaction;
import de.dueto.backend.model.transaction.TransactionAddDTO;
import de.dueto.backend.model.transaction.TransactionMapper;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public long getBalance(User user) {
        return transactionRepository.findAllByUserAmountListContaining(escapeUserId(user.getUserId()))
                .stream()
                .mapToLong(transaction -> transaction.getUserAmountList().get(user.getUserId()))
                .sum();
    }

    public List<Transaction> getTransactions(User user, Group group, long from, long limit) {
        return getTransactions(user, group)
                .stream()
                .skip(from)
                .limit(limit)
                .toList();
    }

    public List<Transaction> getTransactions(User user, Group group) {
        if(group == null) return new ArrayList<>();
        return transactionRepository.findAllByGroupEqualsAndUserAmountListContaining(escapeUserId(user.getUserId()), group.getGroupId());
    }

    public List<Transaction> getTransactions(User user, long from, long limit) {
        return transactionRepository.findAllByUserAmountListContaining(escapeUserId(user.getUserId()))
                .stream()
                .skip(from)
                .limit(limit)
                .toList();
    }

    public boolean addTransaction(TransactionAddDTO transactionAddDTO) {
        Transaction transaction = transactionMapper.fromTransactionAddDTO(transactionAddDTO);
        if(transaction==null) return false;
        transactionRepository.save(transaction);
        return true;
    }

    private String escapeUserId(long userId) {
        return String.format("$.\"%d\"", userId);
    }
}
