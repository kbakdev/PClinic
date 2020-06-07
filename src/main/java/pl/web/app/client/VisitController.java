package pl.web.app.client;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Kacper Bąk
 */
@Controller
class VisitController {

    private final pl.web.app.visit.VisitRepository visits;

    private final DeviceRepository devices;

    public VisitController(pl.web.app.visit.VisitRepository visits, DeviceRepository devices) {
        this.visits = visits;
        this.devices = devices;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure
     * we always have fresh data - Since we do not use the session scope, make sure that
     * Device object always has an id (Even though id is not part of the form fields)
     * @param deviceId
     * @return Device
     */
    @org.springframework.web.bind.annotation.ModelAttribute("visit")
    public pl.web.app.visit.Visit loadDeviceWithVisit(@org.springframework.web.bind.annotation.PathVariable("deviceId") int deviceId, java.util.Map<String, Object> model) {
        Device device = this.devices.findById(deviceId);
        device.setVisitsInternal(this.visits.findByDeviceId(deviceId));
        model.put("device", device);
        pl.web.app.visit.Visit visit = new pl.web.app.visit.Visit();
        device.addVisit(visit);
        return visit;
    }

    // Spring MVC calls method loadDeviceWithVisit(...) before initNewVisitForm is called
    @GetMapping("/clients/*/devices/{deviceId}/visits/new")
    public String initNewVisitForm(@PathVariable("deviceId") int deviceId, Map<String, Object> model) {
        return "devices/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadDeviceWithVisit(...) before processNewVisitForm is called
    @PostMapping("/clients/{clientId}/devices/{deviceId}/visits/new")
    public String processNewVisitForm(@Valid pl.web.app.visit.Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "devices/createOrUpdateVisitForm";
        }
        else {
            this.visits.save(visit);
            return "redirect:/clients/{clientId}";
        }
    }

}
