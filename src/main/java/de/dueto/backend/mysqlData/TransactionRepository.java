package de.dueto.backend.mysqlData;

import de.dueto.backend.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
