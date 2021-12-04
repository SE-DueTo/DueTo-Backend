package de.dueto.backend.mysqlData;

import de.dueto.backend.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(
            value = "SELECT * FROM dueto_transactions WHERE user_amount_list  -> '$.?1' IS NOT NULL",
            nativeQuery = true
    )
    List<Transaction> getAllByUserId(Long userId);

}
