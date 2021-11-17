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
    private long deptId;
    private long amount;
    private long userWhoId;
    private long userWhomId;
    private String paymentMethod;
    private Date date;

}
