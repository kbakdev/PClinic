package pl.web.app.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Controller
class ClientController {

    private static final String VIEWS_CLIENT_CREATE_OR_UPDATE_FORM = "clients/createOrUpdateClientForm";

    private final ClientRepository clients;

    private pl.web.app.visit.VisitRepository visits;

    public ClientController(ClientRepository clinicService, pl.web.app.visit.VisitRepository visits) {
        this.clients = clinicService;
        this.visits = visits;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/clients/new")
    public String initCreationForm(Map<String, Object> model) {
        Client client = new Client();
        model.put("client", client);
        return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/clients/new")
    public String processCreationForm(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.clients.save(client);
            return "redirect:/clients/" + client.getId();
        }
    }

    @GetMapping("/clients/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("client", new Client());
        return "clients/findClients";
    }

    @GetMapping("/clients")
    public String processFindForm(Client client, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /clients to return all records
        if (client.getLastName() == null) {
            client.setLastName(""); // empty string signifies broadest possible search
        }

        // find clients by last name
        Collection<Client> results = this.clients.findByLastName(client.getLastName());
        if (results.isEmpty()) {
            // no clients found
            result.rejectValue("lastName", "notFound", "not found");
            return "clients/findClients";
        }
        else if (results.size() == 1) {
            // 1 client found
            client = results.iterator().next();
            return "redirect:/clients/" + client.getId();
        }
        else {
            // multiple clients found
            model.put("selections", results);
            return "clients/clientsList";
        }
    }

    @GetMapping("/clients/{clientId}/edit")
    public String initUpdateClientForm(@PathVariable("clientId") int clientId, Model model) {
        Client client = this.clients.findById(clientId);
        model.addAttribute(client);
        return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/clients/{clientId}/edit")
    public String processUpdateClientForm(@Valid Client client, BindingResult result,
                                         @PathVariable("clientId") int clientId) {
        if (result.hasErrors()) {
            return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
        }
        else {
            client.setId(clientId);
            this.clients.save(client);
            return "redirect:/clients/{clientId}";
        }
    }

    /**
     * Custom handler for displaying an client.
     * @param clientId the ID of the client to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/clients/{clientId}")
    public ModelAndView showClient(@PathVariable("clientId") int clientId) {
        ModelAndView mav = new ModelAndView("clients/clientDetails");
        Client client = this.clients.findById(clientId);
        for (Device device : client.getDevices()) {
            device.setVisitsInternal(visits.findByDeviceId(device.getId()));
        }
        mav.addObject(client);
        return mav;
    }

}
