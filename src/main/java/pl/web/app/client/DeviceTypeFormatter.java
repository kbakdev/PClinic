package pl.web.app.client;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 * Instructs Spring MVC on how to parse and print elements of type 'DeviceType'. Starting
 * from Spring 3.0, Formatters have come as an improvement in comparison to legacy
 * PropertyEditors.
 *
 * @author Kacper Bąk
 */
@Component
public class DeviceTypeFormatter implements Formatter<DeviceType> {

	private final DeviceRepository devices;

	@Autowired
	public DeviceTypeFormatter(DeviceRepository devices) {
		this.devices = devices;
	}

	@Override
	public String print(DeviceType deviceType, Locale locale) {
		return deviceType.getName();
	}

	@Override
	public DeviceType parse(String text, Locale locale) throws ParseException {
		Collection<DeviceType> findDeviceTypes = this.devices.findDeviceTypes();
		for (DeviceType type : findDeviceTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
