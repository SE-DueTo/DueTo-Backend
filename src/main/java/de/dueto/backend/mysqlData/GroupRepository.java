package de.dueto.backend.mysqlData;

import de.dueto.backend.model.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
