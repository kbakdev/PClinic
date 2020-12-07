package pl.web.app.client;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for <code>Device</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data.
 *
 * @author Kacper Bąk
 */

public interface DeviceRepository extends Repository<Device, Integer> {

	/**
	 * Retrieve all {@link DeviceType}s from the data store.
	 * @return a Collection of {@link DeviceType}s.
	 */
	@Query("SELECT type FROM DeviceType type ORDER BY type.name")
	@Transactional(readOnly = true)
	List<DeviceType> findDeviceTypes();

	/**
	 * Retrieve a {@link Device} from the data store by id.
	 * @param id the id to search for
	 * @return the {@link Device} if found
	 */
	@Transactional(readOnly = true)
	Device findById(Integer id);

	/**
	 * Save a {@link Device} to the data store, either inserting or updating it.
	 * @param device the {@link Device} to save
	 */
	void save(Device device);

}
