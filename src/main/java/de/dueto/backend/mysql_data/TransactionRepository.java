package de.dueto.backend.mysql_data;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserAmountListContaining(String regex);

    List<Transaction> findAllByGroupEqualsAndUserAmountListContaining(Group group, String regex);

}
