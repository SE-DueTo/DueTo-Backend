package de.dueto.backend.model.settle_debt;

import de.dueto.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SettleDebtAddDTO {

    private long groupId;

    private long amount;

    private User debtor;

    private long debtorId;

    private User creditor;

    private long creditorId;

    private String paymentMethod;

    private Date date;

}
