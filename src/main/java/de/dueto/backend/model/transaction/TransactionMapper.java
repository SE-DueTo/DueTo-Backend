package de.dueto.backend.model.transaction;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.mysql_data.GroupRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        Optional<Group> group = groupRepository.findById(transactionAddDTO.getGroupId());
        if(group.isEmpty()) {
            return null;
        }
        return Transaction.builder()
                .group(group.get())
                .amount(transactionAddDTO.getAmount())
                .purpose(transactionAddDTO.getPurpose())
                .paymentMethod(transactionAddDTO.getPaymentMethod())
                .date(transactionAddDTO.getDate())
                .userAmountList(transactionAddDTO.getUserAmountList())
                .repeatingInterval(transactionAddDTO.getRepeatingInterval())
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
