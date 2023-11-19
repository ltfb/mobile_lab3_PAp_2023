package logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;

@Entity
public class Outing {

	@Id
	private String nombre;
	private String fecha;
	private String hora;
	private int maxTourist;
	private String place;
	private LocalDateTime entryDate;
	@Lob // significa que sera guardado cono BLOB en BD
	private byte[] image;
	@JoinTable(name = "Outing_inscription", joinColumns = @JoinColumn(name = "out_name"), inverseJoinColumns = @JoinColumn(name = "inscription"))
	private List<Inscription> inscriptions;

	/**
	 * @param nombre
	 * @param fecha
	 * @param hora
	 * @param maxTourist
	 * @param place
	 * @param entryDate
	 * @param image
	 */
	public Outing(String nombre, String fecha, String hora, int maxTourist, String place, byte[] image) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.maxTourist = maxTourist;
		this.place = place;
		this.entryDate = LocalDateTime.now();
		this.image = image;
		this.inscriptions = new ArrayList<Inscription>();
	}

	public Outing() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
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

	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	/*
	 * public String getImage() { return image; } public void setImage(String image)
	 * { this.image = image; }
	 */

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	public int getFreePlaces() {
		int subscribersNumber = 0;
		for (Inscription inscription : this.getInscriptions()) {
			subscribersNumber += inscription.getTouristsNumber();
		}
		return this.maxTourist - subscribersNumber;
	}

	public boolean addInscription(Inscription nuevo) {
		return this.inscriptions.add(nuevo);
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}