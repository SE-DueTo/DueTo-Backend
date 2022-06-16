package de.dueto.backend.model.transaction;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.GroupRepository;
import de.dueto.backend.mysql_data.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionMapper {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public TransactionMapper(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Transaction fromTransactionAddDTO(TransactionAddDTO transactionAddDTO) {
        Optional<Group> group = groupRepository.findById(transactionAddDTO.getGroupId());
        if(group.isEmpty()) {
            return null;
        }
        Optional<User> user = userRepository.findById(transactionAddDTO.getWhoPaid());
        if(user.isEmpty()) {
            return null;
        }
        return Transaction.builder()
                .whoPaid(user.get())
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
                .whoPaid(transaction.getWhoPaid())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .group(transaction.getGroup())
                .paymentMethod(transaction.getPaymentMethod())
                .purpose(transaction.getPurpose())
                .repeatingInterval(transaction.getRepeatingInterval())
                .userAmountList(transaction.getUserAmountList())
                .build();
    }

}
