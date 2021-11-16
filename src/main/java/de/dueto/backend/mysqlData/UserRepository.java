package de.dueto.backend.mysqlData;

import de.dueto.backend.model.User;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD: Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}