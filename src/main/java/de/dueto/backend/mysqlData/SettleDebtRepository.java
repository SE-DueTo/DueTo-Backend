package de.dueto.backend.mysqlData;

import de.dueto.backend.model.settleDebt.SettleDebt;
import de.dueto.backend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettleDebtRepository extends JpaRepository<SettleDebt, Long> {

    List<SettleDebt> getAllByDebtorEquals(User user);

    List<SettleDebt> getAll

}
