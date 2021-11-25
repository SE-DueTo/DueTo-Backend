package de.dueto.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "settle_debts")
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

    @Column(nullable = false)
    @ManyToOne
    private User userWhoId;

    @Column(nullable = false)
    @ManyToOne
    private User userWhomId;

    @Column(nullable = false)
    private String paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

}
