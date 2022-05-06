package de.dueto.backend.model.settle_debt;

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

    private long debtorId;

    private long creditorId;

    private String paymentMethod;

    private Date date;

}
