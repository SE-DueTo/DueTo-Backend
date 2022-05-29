package de.dueto.backend.service;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.transaction.Transaction;
import de.dueto.backend.model.transaction.TransactionAddDTO;
import de.dueto.backend.model.transaction.TransactionMapper;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.TransactionRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
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

    public long getBalance(@NonNull User user) {
        return transactionRepository.findAllByUserAmountListContaining(escapeUserId(user.getUserId()))
                .stream()
                .mapToLong(transaction -> transaction.getUserAmountList().get(user.getUserId()))
                .sum();
    }

    @NonNull
    public List<Transaction> getTransactions(@NonNull User user, @NonNull Group group, long from, long limit) {
        return getTransactions(user, group)
                .stream()
                .skip(from)
                .limit(limit)
                .toList();
    }

    @NonNull
    public List<Transaction> getTransactions(@NonNull User user, @Nullable Group group) {
        if(group == null) return new ArrayList<>();
        return transactionRepository.findAllByGroupEqualsAndUserAmountListContaining(escapeUserId(user.getUserId()), group.getGroupId());
    }

    @NonNull
    public List<Transaction> getTransactions(@NonNull User user, long from, long limit) {
        return transactionRepository.findAllByUserAmountListContaining(escapeUserId(user.getUserId()))
                .stream()
                .skip(from)
                .limit(limit)
                .toList();
    }

    public boolean addTransaction(@NonNull TransactionAddDTO transactionAddDTO) {
        Transaction transaction = transactionMapper.fromTransactionAddDTO(transactionAddDTO);
        if(transaction==null) return false;
        transactionRepository.save(transaction);
        return true;
    }

    private String escapeUserId(long userId) {
        return String.format("$.\"%d\"", userId);
    }
}
