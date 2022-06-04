package de.dueto.backend.mysql_data;

import de.dueto.backend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);
    List<User> findByUsernameContaining(String username);
}