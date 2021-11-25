package de.dueto.backend.mysqlData;

import de.dueto.backend.model.SettleDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleDebtRepository extends JpaRepository<SettleDebt, Long> {
}
