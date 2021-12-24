package de.dueto.backend.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionAddDTO {

    private long groupId;

    private long amount;

    private String purpose;

    private String paymentMethod;

    private Date date;

    private HashMap<Long, Long> userAmountList;

    private Long repeatingInterval;

}
