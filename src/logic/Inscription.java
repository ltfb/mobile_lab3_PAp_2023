package logic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Inscription {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String inscriptionDate;
	private int touristsNumber;
	private float cost;
	@JoinColumn(name="tourist")
	private Tourist tourist;
	
	public Inscription() {};

	public Inscription(String inscriptionDate, int touristsNumber, int cost, Tourist tourist) {
		this.inscriptionDate = inscriptionDate;
		this.touristsNumber = touristsNumber;
		this.cost = cost;
		this.tourist = tourist;
	}

	public String getInscriptionDate() {
		return inscriptionDate;
	}

	public void setInscriptionDate(String inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	public int getTouristsNumber() {
		return touristsNumber;
	}

	public void setTouristsNumber(int touristsNumber) {
		this.touristsNumber = touristsNumber;
	}

	public Tourist getTourist() {
		return this.tourist;
	}

	public void setTourist(Tourist tourist) {
		this.tourist = tourist;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
