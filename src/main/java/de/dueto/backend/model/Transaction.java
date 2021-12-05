package de.dueto.backend.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

@Table(name = "dueto_transactions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "transaction_id")
    private long transactionId;

    @ManyToOne
    private Group group;

    private long amount;

    private String purpose;

    private String paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private HashMap<Long, Long> userAmountList;

    @Column(name = "repeating_interval")
    private long repeatingInterval;

}