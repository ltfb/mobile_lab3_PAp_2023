package logic;

import java.time.LocalDateTime;

import enums.ActivityState;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class DataTypeActivity {

	private String name;
	private String description;
	private int duration;
	private String cost;
	private String city;
	private LocalDateTime entryDate;
	private ActivityState state;
	private byte[] image;
	private DataTypeOuting[] outings;

	/**
	 * @param name
	 * @param description
	 * @param length
	 * @param cost
	 * @param entryDate
	 * @param state
	 * @param image
	 */
	public DataTypeActivity(String name, String description, int length, String cost, String city,
			LocalDateTime entryDate, byte[] image, DataTypeOuting[] outings) {
		this.name = name;
		this.description = description;
		this.duration = length;
		this.cost = cost;
		this.city = city;
		this.entryDate = entryDate;
		this.state = ActivityState.AGREGADA;
		this.image = image;
		this.outings = outings;
	}

	public DataTypeActivity(String name, String description, int length, String cost, String city,
			LocalDateTime entryDate, byte[] image) {
		this.name = name;
		this.description = description;
		this.duration = length;
		this.cost = cost;
		this.city = city;
		this.entryDate = entryDate;
		this.state = ActivityState.AGREGADA;
		this.image = image;
		this.outings = new DataTypeOuting[0];
	}

	public DataTypeActivity(String name, String description, int length, String cost, String city,
			LocalDateTime entryDate) {
		this.name = name;
		this.description = description;
		this.duration = length;
		this.cost = cost;
		this.city = city;
		this.entryDate = entryDate;
		this.state = ActivityState.AGREGADA;
	}

	public DataTypeActivity(String name, String description, int length, String cost, String city, byte[] image) {
		this.name = name;
		this.description = description;
		this.duration = length;
		this.cost = cost;
		this.city = city;
		this.entryDate = LocalDateTime.now();
		this.state = ActivityState.AGREGADA;
		this.image = image;
		this.outings = new DataTypeOuting[0];
	}

	public DataTypeActivity(String name, String description, int length, String cost, String city,
			LocalDateTime entryDate, DataTypeOuting[] outings) {
		this.name = name;
		this.description = description;
		this.duration = length;
		this.cost = cost;
		this.city = city;
		this.entryDate = entryDate;
		this.state = ActivityState.AGREGADA;
		this.outings = outings;
	}

	public DataTypeActivity(String name, String description, int length, String cost, String city,
			DataTypeOuting[] outings) {
		this.name = name;
		this.description = description;
		this.duration = length;
		this.cost = cost;
		this.city = city;
		this.entryDate = LocalDateTime.now();
		this.state = ActivityState.AGREGADA;
		this.outings = outings;
	}

	/**
	 * @param activity
	 */
	public DataTypeActivity(Activity activity) {
		this.name = activity.getName();
		this.description = activity.getDescription();
		this.duration = activity.getDuration();
		this.cost = activity.getCost();
		this.city = activity.getCity();
		this.entryDate = activity.getEntryDate();
		this.state = activity.getState();
		this.image = activity.getImage();
		this.outings = activity.getOutings();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	public ActivityState getState() {
		return state;
	}

	public void setState(ActivityState state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return the outings
	 */
	public DataTypeOuting[] getOutings() {
		return outings;
	}

	/**
	 * @param outings the outings to set
	 */
	public void setOutings(DataTypeOuting[] outings) {
		this.outings = outings;
	}

	/*
	 * Sirve para mostrar textualmente la informacion de la Actividad, por ejemplo
	 * en un ComboBox
	 */
	public String toString() {
		return getName();
	}

}