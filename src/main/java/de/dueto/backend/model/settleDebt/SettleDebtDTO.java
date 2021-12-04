package de.dueto.backend.model.settleDebt;

import de.dueto.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SettleDebtDTO {

    private long debtId;

    private long groupId;

    private long amount;

    private User debtor;

    private long debtorId;

    private User creditor;

    private long creditorId;

    private String paymentMethod;

    private Date date;

}
