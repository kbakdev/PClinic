package pl.web.app.servicer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Kacper Bąk
 */
@Controller
class ServicerController {

	private final ServicerRepository servicers;

	public ServicerController(ServicerRepository clinicService) {
		this.servicers = clinicService;
	}

	@GetMapping("/servicers.html")
	public String showServicerList(Map<String, Object> model) {
		// Here we are returning an object of type 'Servicers' rather than a collection of
		// Servicer
		// objects so it is simpler for Object-Xml mapping
		Servicers servicers = new Servicers();
		servicers.getServicerList().addAll(this.servicers.findAll());
		model.put("servicers", servicers);
		return "servicers/servicerList";
	}

	@GetMapping({ "/servicers" })
	public @ResponseBody Servicers showResourcesServicerList() {
		// Here we are returning an object of type 'Servicers' rather than a collection of
		// Servicer
		// objects so it is simpler for JSon/Object mapping
		Servicers servicers = new Servicers();
		servicers.getServicerList().addAll(this.servicers.findAll());
		return servicers;
	}

}
