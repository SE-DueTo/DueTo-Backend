package de.dueto.backend.mysql_data;

import de.dueto.backend.model.settle_debt.SettleDebt;
import de.dueto.backend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettleDebtRepository extends JpaRepository<SettleDebt, Long> {

    List<SettleDebt> getAllByDebtorEquals(User user);

    List<SettleDebt> getAllByDebtorEqualsAndGroupEquals(User debtor, long group);

}
