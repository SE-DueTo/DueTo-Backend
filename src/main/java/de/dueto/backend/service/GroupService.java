package de.dueto.backend.service;

import de.dueto.backend.model.settle_debt.SettleDebt;
import de.dueto.backend.model.transaction.Transaction;
import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.group.GroupAddNormalDTO;
import de.dueto.backend.model.group.GroupAndSumDTO;
import de.dueto.backend.model.group.GroupMapper;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.GroupRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

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
                .sum(getBalance(user, group.get()))
                .build();
    }

    public long getBalance(@NonNull User user, Group group) {
        List<Transaction> transactions = transactionService.getTransactions(user, group);
        List<SettleDebt> debts = getDebts(user, group);

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

    public List<SettleDebt> getDebts(@NonNull User user, Group group) {
        return settleDebtService.getDebts(user, group);
    }

    public long addNormalGroup(GroupAddNormalDTO groupAddNormalDTO) {
        Group group = groupMapper.fromGroupAddNormalDTO(groupAddNormalDTO);
        if(group == null || group.getUsers().isEmpty()) return -1;
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

    @Nullable
    public Group getGroupById(long groupId) {
        return groupRepository.findById(groupId).orElse(null);
    }
}
