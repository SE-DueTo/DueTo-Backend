package de.dueto.backend.mysqlData;

import de.dueto.backend.model.SettleDebt;
import org.springframework.data.repository.CrudRepository;

public interface SettleDebtRepository extends CrudRepository<SettleDebt, Long> {
}
