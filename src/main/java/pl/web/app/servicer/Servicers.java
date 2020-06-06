package pl.web.app.servicer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple domain object representing a list of servicemans. Mostly here to be used for
 * the 'servicers' {@link org.springframework.web.servlet.view.xml.MarshallingView}.
 *
 * @author Kacper Bąk
 */
@XmlRootElement
public class Servicers {

    private List<Servicer> servicers;

    @XmlElement
    public List<Servicer> getServicerList() {
        if (servicers == null) {
            servicers = new ArrayList<>();
        }
        return servicers;
    }

}