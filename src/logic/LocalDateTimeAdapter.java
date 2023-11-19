package logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public String marshal(LocalDateTime dateTime) {
		return dateTime.format(dateFormat);
	}

	@Override
	public LocalDateTime unmarshal(String dateTime) {
		return LocalDateTime.parse(dateTime, dateFormat);
	}

}