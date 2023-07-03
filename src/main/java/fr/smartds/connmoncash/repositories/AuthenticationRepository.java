package fr.smartds.connmoncash.repositories;

import fr.smartds.connmoncash.entities.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
    //@Query("SELECT * FROM AUTHENTICATION WHERE id = (SELECT MAX(id) FROM AUTHENTICATION)")
   // Authentication getOldToken();

}
