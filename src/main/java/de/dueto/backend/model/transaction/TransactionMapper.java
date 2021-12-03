package de.dueto.backend.model.transaction;

import de.dueto.backend.mysqlData.GroupRepository;
import org.springframework.stereotype.Component;

@Component
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

    public TransactionDTO fromTransaction(Transaction transaction) {

        return TransactionDTO.builder()
                .transactionId(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .groupId(transaction.getGroup().getGroupId())
                .paymentMethod(transaction.getPaymentMethod())
                .purpose(transaction.getPurpose())
                .repeatingInterval(transaction.getRepeatingInterval())
                .userAmountList(transaction.getUserAmountList())
                .build();
    }

}
