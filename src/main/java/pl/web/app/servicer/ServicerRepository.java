package pl.web.app.servicer;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for <code>Servicer</code> domain objects All method names are
 * compliant with Spring Data naming conventions so this interface can easily be extended
 * for Spring Data.
 *
 * @author Kacper Bąk
 */
public interface ServicerRepository extends Repository<Servicer, Integer> {

	/**
	 * Retrieve all <code>Servicer</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Servicer</code>s
	 */
	@Transactional(readOnly = true)
	@Cacheable("servicers")
	Collection<Servicer> findAll() throws DataAccessException;

}