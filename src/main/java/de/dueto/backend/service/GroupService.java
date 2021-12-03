package de.dueto.backend.service;

import de.dueto.backend.model.SettleDebt;
import de.dueto.backend.model.Transaction;
import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.group.GroupAddNormalDTO;
import de.dueto.backend.model.group.GroupAndSumDTO;
import de.dueto.backend.model.group.GroupMapper;
import de.dueto.backend.model.settleDebt.SettleDebt;
import de.dueto.backend.model.transaction.Transaction;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysqlData.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final TransactionService transactionService;
    private final SettleDebtService settleDebtService;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, TransactionService transactionService, SettleDebtService settleDebtService, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.transactionService = transactionService;
        this.settleDebtService = settleDebtService;
        this.groupMapper = groupMapper;
    }

    public GroupAndSumDTO getGroupInfo(User user, long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if(group.isEmpty()) return null;
        return GroupAndSumDTO.builder()
                .group(group.get())
                .sum(getBalance(user, groupId))
                .build();
    }

    public long getBalance(User user, long groupId) {
        List<Transaction> transactions = getTransactions(user, groupId);
        List<SettleDebt> debts = getDebts(user, groupId);

        long transactionBalance = transactions
                .stream()
                .mapToLong(transaction -> transaction.getUserAmountList().get(user.getUserId()))
                .sum();

        long debtsBalance = debts
                .stream()
                .mapToLong(SettleDebt::getAmount)
                .sum();

        return debtsBalance - transactionBalance;
    }

    public List<SettleDebt> getDebts(User user, long groupId) {
        return settleDebtService.getDebts(user, groupId);
    }

    public List<Transaction> getTransactions(User user, long groupId) {
        return transactionService.getTransactions(user, groupId);
    }

    public List<Transaction> getTransactions(User user, long groupId, long from, long limit) {
        return transactionService.getTransactions(user, groupId, from, limit);
    }

    public long addNormalGroup(User user, GroupAddNormalDTO groupAddNormalDTO) {
        Group group = groupMapper.fromGroupAddNormalDTO(groupAddNormalDTO);
        if(group == null || group.getUsers().size() == 0) return -1;
        return groupRepository.save(group).getGroupId();
    }

    public long addSpontaneousGroup(User user, long userId) {
        Group group = groupMapper.fromGroupAddSpontaneous(user, userId);
        if(group == null) return -1;
        return groupRepository.save(group).getGroupId();
    }

    public boolean removeNormalGroup(long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if(group.isEmpty()) return false;
        groupRepository.deleteById(groupId);
        return true;
    }
}
