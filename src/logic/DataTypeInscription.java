package logic;

public class DataTypeInscription {

	String inscriptionDate;
	int touristNumber;
	String touristNickname;
	float costo;

	public DataTypeInscription() {

	}

	public DataTypeInscription(String inscriptionDate, int touristNumber, String touristNickname, float costo) {
		this.inscriptionDate = inscriptionDate;
		this.touristNumber = touristNumber;
		this.touristNickname = touristNickname;
		this.costo = costo;
	}

	public DataTypeInscription(Inscription inscription) {
		this.inscriptionDate = inscription.getInscriptionDate();
		this.touristNumber = inscription.getTouristsNumber();
		this.touristNickname = inscription.getTourist().getNickname();
		this.costo = inscription.getCost();
	}

	public String getInscriptionDate() {
		return inscriptionDate;
	}

	public void setInscriptionDate(String inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	public int getTouristNumber() {
		return touristNumber;
	}

	public void setTouristNumber(int touristNumber) {
		this.touristNumber = touristNumber;
	}

	public String getTouristNickname() {
		return touristNickname;
	}

	public void setTouristNickname(String touristNickname) {
		this.touristNickname = touristNickname;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

}
