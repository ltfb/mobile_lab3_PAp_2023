package logic;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import enums.ActivityState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Activity {

	@Id
	private String name;
	private String description;
	private int duration;
	private String cost;
	private String city;
	private LocalDateTime entryDate;

	@Lob // significa que sera guardado cono BLOB en BD
	private byte[] image;

	@OneToMany
	@JoinTable(name = "Act_outing", joinColumns = @JoinColumn(name = "act_name"), inverseJoinColumns = @JoinColumn(name = "out_name"))
	private Map<String, Outing> outingMap;
	@Enumerated(EnumType.ORDINAL)
	private ActivityState state;

	public Activity() {
	};

	public Activity(String name, String description, int duration, String cost, String city, LocalDateTime entryDate,
			byte[] image) {
		super();
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.cost = cost;
		this.city = city;
		this.entryDate = entryDate;
		this.outingMap = new HashMap<String, Outing>();
		this.state = ActivityState.AGREGADA;
		this.image = image;
	}

	public Activity(String name, String description, int duration, String cost, String city) {
		super();
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.cost = cost;
		this.city = city;
		this.entryDate = LocalDateTime.now();
		this.state = ActivityState.AGREGADA;
		this.outingMap = new HashMap<String, Outing>();
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

	public LocalDateTime getEntryDate() {
		return this.entryDate;
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

	/*
	 * public ImageIO getImage() { return image; } public void setImage(ImageIO
	 * image) { this.image = image; }
	 */
	public DataTypeOuting[] getOutings() {
		DataTypeOuting[] out = new DataTypeOuting[this.outingMap.size()];
		int iter = 0;

		// Mientras itero voy creando los data y aniadiendolos en el array
		for (Entry<String, Outing> entry : this.outingMap.entrySet()) {
			DataTypeOuting n = new DataTypeOuting(entry.getValue().getNombre(), entry.getValue().getFecha(),
					entry.getValue().getHora(), entry.getValue().getMaxTourist(), entry.getValue().getPlace(),
					entry.getValue().getFreePlaces(), entry.getValue().getEntryDate(),entry.getValue().getImage());
			out[iter] = n;
			iter++;
		}
		return out;
	}

	public Map<String, Outing> getOutingMap() {
		return outingMap;
	}

	public void setOutingMap(Map<String, Outing> outingMap) {
		this.outingMap = outingMap;
	}

	public boolean addOuting(Outing outing) {
		if (this.outingMap.putIfAbsent(outing.getNombre(), outing) != null) {
			return false;
		}
		return true;
	}

	public DataTypeOuting getOuting(String outingName) {
		Outing myOut = this.outingMap.get(outingName);
		DataTypeOuting out = new DataTypeOuting(myOut.getNombre(), myOut.getFecha(), myOut.getHora(),
				myOut.getMaxTourist(), myOut.getPlace(), myOut.getFreePlaces(), myOut.getEntryDate(), myOut.getImage());
		return out;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
