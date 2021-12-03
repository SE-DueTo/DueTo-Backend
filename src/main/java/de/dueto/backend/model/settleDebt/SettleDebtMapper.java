package de.dueto.backend.model.settleDebt;

import de.dueto.backend.mysqlData.GroupRepository;
import de.dueto.backend.mysqlData.UserRepository;

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

}
