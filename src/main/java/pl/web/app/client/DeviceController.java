package pl.web.app.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/clients/{clientId}")
class DeviceController {

	private static final String VIEWS_DEVICES_CREATE_OR_UPDATE_FORM = "devices/createOrUpdateDeviceForm";

	private final DeviceRepository devices;

	private final ClientRepository clients;

	public DeviceController(DeviceRepository devices, ClientRepository clients) {
		this.devices = devices;
		this.clients = clients;
	}

	@ModelAttribute("types")
	public Collection<DeviceType> populateDeviceTypes() {
		return this.devices.findDeviceTypes();
	}

	@org.springframework.web.bind.annotation.ModelAttribute("client")
	public pl.web.app.client.Client findClient(
			@org.springframework.web.bind.annotation.PathVariable("clientId") int clientId) {
		return this.clients.findById(clientId);
	}

	@InitBinder("client")
	public void initClientBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("device")
	public void initDeviceBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new DeviceValidator());
	}

	@GetMapping("/devices/new")
	public String initCreationForm(Client client, ModelMap model) {
		Device device = new Device();
		client.addDevice(device);
		model.put("device", device);
		return VIEWS_DEVICES_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/devices/new")
	public String processCreationForm(Client client, @Valid Device device, BindingResult result, ModelMap model) {
		if (StringUtils.hasLength(device.getName()) && device.isNew()
				&& client.getDevice(device.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}
		client.addDevice(device);
		if (result.hasErrors()) {
			model.put("device", device);
			return VIEWS_DEVICES_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.devices.save(device);
			return "redirect:/clients/{clientId}";
		}
	}

	@GetMapping("/devices/{deviceId}/edit")
	public String initUpdateForm(@PathVariable("deviceId") int deviceId, ModelMap model) {
		Device device = this.devices.findById(deviceId);
		model.put("device", device);
		return VIEWS_DEVICES_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/devices/{deviceId}/edit")
	public String processUpdateForm(@Valid Device device, BindingResult result, Client client, ModelMap model) {
		if (result.hasErrors()) {
			device.setClient(client);
			model.put("device", device);
			return VIEWS_DEVICES_CREATE_OR_UPDATE_FORM;
		}
		else {
			client.addDevice(device);
			this.devices.save(device);
			return "redirect:/clients/{clientId}";
		}
	}

}
