package de.dueto.backend.model.transaction;

import de.dueto.backend.mysqlData.GroupRepository;

public class TransactionMapper {

    private final GroupRepository groupRepository;

    public TransactionMapper(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Transaction fromTransactionAddDTO(TransactionAddDTO transactionAddDTO) {
        if (groupRepository.findById(transactionAddDTO.getGroupId()).isEmpty()) {
            return null;
        }
        return Transaction.builder()
                .group(groupRepository.findById(transactionAddDTO.getGroupId()).get())
                .amount(transactionAddDTO.getAmount())
                .purpose(transactionAddDTO.getPurpose())
                .paymentMethod(transactionAddDTO.getPaymentMethod())
                .date(transactionAddDTO.getDate())
                .userAmountList(transactionAddDTO.getUserAmountList())
                .repeatingInterval(transactionAddDTO.getRepeatingInterval().orElse(null))
                .build();
    }

    public Transaction fromTransaction(Transaction transaction) {

        return Transaction.builder()
                .group(groupRepository.findById(transaction.getGroup().getGroupId()).get())
                .amount(transaction.getAmount())
                .purpose(transaction.getPurpose())
                .paymentMethod(transaction.getPaymentMethod())
                .date(transaction.getDate())
                .userAmountList(transaction.getUserAmountList())
                .repeatingInterval(transaction.getRepeatingInterval())
                .build();
    }

}
