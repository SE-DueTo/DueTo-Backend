package de.dueto.backend.mysql_data;

import de.dueto.backend.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuetoSessionRepository extends JpaRepository<Session, String> {
}
