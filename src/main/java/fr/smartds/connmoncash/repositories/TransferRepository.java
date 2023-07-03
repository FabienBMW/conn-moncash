package fr.smartds.connmoncash.repositories;

import fr.smartds.connmoncash.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
