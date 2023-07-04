package fr.smartds.connmoncash.repositories;

import fr.smartds.connmoncash.entities.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
    Optional<Authentication> findFirstByOrderByDateDesc();
//    @Query("SELECT * FROM AUTHENTICATION WHERE id = (SELECT MAX(id) FROM AUTHENTICATION)")
//    Authentication getOldToken();

}
