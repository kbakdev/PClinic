package pl.web.app.client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import pl.web.app.visit.Visit;
import pl.web.app.model.NamedEntity;

@Entity
@Table(name = "devices")
public class Device extends pl.web.app.model.NamedEntity {

    @Column(name = "bought_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate boughtDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private DeviceType type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Transient
    private Set<pl.web.app.visit.Visit> visits = new LinkedHashSet<>();

    public void setBoughtDate(LocalDate boughtDate) {
        this.boughtDate = boughtDate;
    }

    public LocalDate getBoughtDate() {
        return this.boughtDate;
    }

    public DeviceType getType() {
        return this.type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public Client getClient() {
        return this.client;
    }

    protected void setClient(Client client) {
        this.client = client;
    }

    protected Set<pl.web.app.visit.Visit> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new HashSet<>();
        }
        return this.visits;
    }

    protected void setVisitsInternal(Collection<pl.web.app.visit.Visit> visits) {
        this.visits = new LinkedHashSet<>(visits);
    }

    public List<pl.web.app.visit.Visit> getVisits() {
        List<pl.web.app.visit.Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisit(pl.web.app.visit.Visit visit) {
        getVisitsInternal().add(visit);
        visit.setDeviceId(this.getId());
    }

}
