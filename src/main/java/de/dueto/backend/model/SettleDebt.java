package de.dueto.backend.model;

import de.dueto.backend.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "dueto_settle_debts")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SettleDebt {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    private long deptId;

    @Column(nullable = false)
    private long amount;

    @ManyToOne
    private User debtor;

    @ManyToOne
    private User creditor;

    @Column(nullable = false)
    private String paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

}
