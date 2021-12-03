package de.dueto.backend.model.settleDebt;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "dueto_settle_debts")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SettleDebt {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    private long deptId;

    @Column(nullable = false)
    private long amount;

    @ManyToOne
    private Group group;

    @ManyToOne
    private User debtor;
    private long debtorId;

    @ManyToOne
    private User creditor;
    private long creditorId;

    @Column(nullable = false)
    private String paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

}
