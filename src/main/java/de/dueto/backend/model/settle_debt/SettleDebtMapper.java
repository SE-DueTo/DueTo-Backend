package de.dueto.backend.model.settle_debt;

import de.dueto.backend.mysql_data.GroupRepository;
import de.dueto.backend.mysql_data.UserRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SettleDebtMapper {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public SettleDebtMapper(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public SettleDebt fromSettleDebtAddDTO(SettleDebtAddDTO settleDebtAddDTO) {
        if (groupRepository.findById(settleDebtAddDTO.getGroupId()).isEmpty()) {
            return null;
        }
        return SettleDebt.builder()
                .group(groupRepository.findById(settleDebtAddDTO.getGroupId()).get())
                .amount(settleDebtAddDTO.getAmount())
                .creditor(userRepository.findById(settleDebtAddDTO.getCreditorId()).get())
                .debtor(userRepository.findById(settleDebtAddDTO.getDebtorId()).get())
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
