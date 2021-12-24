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
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final GroupService groupService;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, GroupService groupService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.groupService = groupService;
    }

    public long getBalance(User user) {
        return transactionRepository.findAllByUserAmountListContaining(user.getUserId() + "")
                .stream()
                .mapToLong(transaction -> transaction.getUserAmountList().get(user.getUserId()))
                .sum();
    }

    public List<Transaction> getTransactions(User user, long groupId, long from, long limit) {
        return getTransactions(user, groupId)
                .stream()
                .skip(from)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactions(User user, long groupId) {
        Group group = groupService.getGroupById(groupId);
        if(group == null) return new ArrayList<>();
        return transactionRepository.findAllByGroupEqualsAndUserAmountListContaining(group, user.getUserId() + "");
    }

    public List<Transaction> getTransactions(User user, long from, long limit) {
        return transactionRepository.findAllByUserAmountListContaining(user.getUserId() + "")
                .stream()
                .skip(from)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public boolean addTransaction(TransactionAddDTO transactionAddDTO) {
        Transaction transaction = transactionMapper.fromTransactionAddDTO(transactionAddDTO);
        if(transaction==null) return false;
        transactionRepository.save(transaction);
        return true;
    }
}
