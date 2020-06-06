package pl.web.app.servicer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Models a {@link Servicer Servicer's} specialty.
 *
 * @author Kacper Bąk
 */
@Entity
@Table(name = "specialties")
public class Specialty extends pl.web.app.model.NamedEntity implements Serializable {

}
