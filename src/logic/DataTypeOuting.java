package logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class DataTypeOuting {
	private String name;
	private String date;
	private String hour;
	private int maxTourist;
	private String place;
	private LocalDateTime entryDate;
	private int freePlaces;
	private byte[] image;
	private List<DataTypeInscription> inscriptions;

	public DataTypeOuting() {
	}

	public DataTypeOuting(Outing outing) {
		this.name = outing.getNombre();
		this.date = outing.getFecha();
		this.hour = outing.getHora();
		this.maxTourist = outing.getMaxTourist();
		this.place = outing.getPlace();
		this.entryDate = outing.getEntryDate();
		this.image = outing.getImage();
		this.inscriptions = new ArrayList<>();
		for (Inscription ins : outing.getInscriptions()) {
			this.inscriptions.add(new DataTypeInscription(ins));
		}
	}

	public DataTypeOuting(String nombre, String fecha, String hora, int maxTourist, String place, LocalDateTime time) {
		this.name = nombre;
		this.date = fecha;
		this.hour = hora;
		this.maxTourist = maxTourist;
		this.place = place;
		this.freePlaces = maxTourist;
		this.entryDate = time;
		this.image = null;
		this.inscriptions = new ArrayList<>();
	} // este es usado cuando creamos un outing, no importal el atributo freePlaces.

	public DataTypeOuting(String nombre, String fecha, String hora, int maxTourist, String place, int frP,
			LocalDateTime time) {
		this.name = nombre;
		this.date = fecha;
		this.hour = hora;
		this.maxTourist = maxTourist;
		this.place = place;
		this.freePlaces = frP;
		this.entryDate = time;
		this.image = null;
		this.inscriptions = new ArrayList<>();
	} // este es usado cuando se pide informacion de un outing, te devuelve los
		// lugares libres.

	public DataTypeOuting(String nombre, String fecha, String hora, int maxTourist, String place, int frP,
			LocalDateTime time, byte[] image) {
		this.name = nombre;
		this.date = fecha;
		this.hour = hora;
		this.maxTourist = maxTourist;
		this.place = place;
		this.freePlaces = frP;
		this.entryDate = time;
		this.image = image;
		this.inscriptions = new ArrayList<>();
	}

	public DataTypeOuting(String nombre, String fecha, String hora, int maxTourist, String place, LocalDateTime time,
			byte[] image) {
		this.name = nombre;
		this.date = fecha;
		this.hour = hora;
		this.maxTourist = maxTourist;
		this.place = place;
		this.entryDate = time;
		this.image = image;
		this.inscriptions = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public int getMaxTourist() {
		return maxTourist;
	}

	public void setMaxTourist(int maxTourist) {
		this.maxTourist = maxTourist;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	public int getFreePlaces() {
		return freePlaces;
	}

	public void setFreePlaces(int freePlaces) {
		this.freePlaces = freePlaces;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @return the inscriptions
	 */
	public List<DataTypeInscription> getInscriptions() {
		return inscriptions;
	}

	/**
	 * @param inscriptions the inscriptions to set
	 */
	public void setInscriptions(List<DataTypeInscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	@Override
	public String toString() {
		return getName();
	}
}
