package de.dueto.backend.model.settle_debt;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.GroupRepository;
import de.dueto.backend.mysql_data.UserRepository;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class SettleDebtMapper {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public SettleDebtMapper(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public SettleDebt fromSettleDebtAddDTO(SettleDebtAddDTO settleDebtAddDTO) {
        Optional<Group> group = groupRepository.findById(settleDebtAddDTO.getGroupId());
        Optional<User> creditor = userRepository.findById(settleDebtAddDTO.getCreditorId());
        Optional<User> debtor = userRepository.findById(settleDebtAddDTO.getDebtorId());
        if (group.isEmpty() || creditor.isEmpty() || debtor.isEmpty()) {
            return null;
        }


        return SettleDebt.builder()
                .group(group.get())
                .amount(settleDebtAddDTO.getAmount())
                .creditor(creditor.get())
                .debtor(debtor.get())
                .paymentMethod(settleDebtAddDTO.getPaymentMethod())
                .date(settleDebtAddDTO.getDate())
                .build();
    }

    public SettleDebtDTO fromSettleDebt(SettleDebt settleDebt) {

        return SettleDebtDTO.builder()
                .debtId(settleDebt.getDebtId())
                .amount(settleDebt.getAmount())
                .groupId(settleDebt.getGroup().getGroupId())
                .debtor(settleDebt.getDebtor())
                .debtorId(settleDebt.getDebtorId())
                .creditor(settleDebt.getCreditor())
                .creditorId(settleDebt.getCreditorId())
                .paymentMethod(settleDebt.getPaymentMethod())
                .date(settleDebt.getDate())
                .build();
    }

}
