package pl.web.app.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "clients")
public class Client extends pl.web.app.model.Person {

    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<pl.web.app.client.Device> devices;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    protected Set<pl.web.app.client.Device> getDevicesInternal() {
        if (this.devices == null) {
            this.devices = new HashSet<>();
        }
        return this.devices;
    }

    protected void setDevicesInternal(Set<pl.web.app.client.Device> devices) {
        this.devices = devices;
    }

    public List<pl.web.app.client.Device> getDevices() {
        List<pl.web.app.client.Device> sortedDevices = new ArrayList<>(getDevicesInternal());
        PropertyComparator.sort(sortedDevices, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedDevices);
    }

    public void addDevice(pl.web.app.client.Device device) {
        if (device.isNew()) {
            getDevicesInternal().add(device);
        }
        device.setClient(this);
    }

    /**
     * Return the Device with the given name, or null if none found for this Client.
     * @param name to test
     * @return true if device name is already in use
     */
    public pl.web.app.client.Device getDevice(String name) {
        return getDevice(name, false);
    }

    /**
     * Return the Device with the given name, or null if none found for this Client.
     * @param name to test
     * @return true if device name is already in use
     */
    public pl.web.app.client.Device getDevice(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (pl.web.app.client.Device device : getDevicesInternal()) {
            if (!ignoreNew || !device.isNew()) {
                String compName = device.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return device;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew()).append("lastName", this.getLastName())
                .append("firstName", this.getFirstName()).append("address", this.address).append("city", this.city)
                .append("telephone", this.telephone).toString();
    }

}