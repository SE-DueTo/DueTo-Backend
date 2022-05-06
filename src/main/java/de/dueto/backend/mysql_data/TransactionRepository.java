package de.dueto.backend.mysql_data;

import de.dueto.backend.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = """
            SELECT t FROM Transaction t
                WHERE FUNCTION('JSON_EXTRACT', t.userAmountList, ?1) IS NOT NULL
            """)
    List<Transaction> findAllByUserAmountListContaining(String escapedUserId);

    @Query(value = """
            SELECT t FROM Transaction t
                WHERE FUNCTION('JSON_EXTRACT', t.userAmountList, ?1) IS NOT NULL AND t.group.groupId = ?2
            """)
    List<Transaction> findAllByGroupEqualsAndUserAmountListContaining(String escapedUserId, long groupId);

}
