package de.dueto.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

@Table(name = "transactions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long transactionId;
    private long groupOrUserId;
    private long amount;
    private String purpose;
    private String paymentMethod;
    private Date date;
    @ElementCollection
    private HashMap<User, Long> userAmountList;
    private long repeating_interval;

}
