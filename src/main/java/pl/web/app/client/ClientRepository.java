package pl.web.app.client;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for <code>Client</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data.
 */
public interface ClientRepository extends Repository<Client, Integer> {

    /**
     * Retrieve {@link Client}s from the data store by last name, returning all clients
     * whose last name <i>starts</i> with the given name.
     * @param lastName Value to search for
     * @return a Collection of matching {@link Client}s (or an empty Collection if none
     * found)
     */
    @Query("SELECT DISTINCT client FROM Client client left join fetch client.devices WHERE client.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    Collection<Client> findByLastName(@Param("lastName") String lastName);

    /**
     * Retrieve an {@link Client} from the data store by id.
     * @param id the id to search for
     * @return the {@link Client} if found
     */
    @Query("SELECT client FROM Client client left join fetch client.devices WHERE client.id =:id")
    @Transactional(readOnly = true)
    Client findById(@Param("id") Integer id);

    /**
     * Save an {@link Client} to the data store, either inserting or updating it.
     * @param client the {@link Client} to save
     */
    void save(Client client);

}
