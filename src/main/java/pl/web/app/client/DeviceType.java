package pl.web.app.client;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kacper Bąk Device can be a smartphone, computer, laptop, etc...
 */

@Entity
@Table(name = "types")
public class DeviceType extends pl.web.app.model.NamedEntity {

}