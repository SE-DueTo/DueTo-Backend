package de.dueto.backend.model.transaction;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionDTO {

    private long transactionId;

    private User whoPaid;

    private Group group;

    private long amount;

    private String purpose;

    private String paymentMethod;

    private Date date;

    private HashMap<Long, Long> userAmountList;

    private Long repeatingInterval;
}
