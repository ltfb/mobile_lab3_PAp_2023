package logic;

import java.util.ArrayList;
import java.util.List;

import exceptions.UserException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("turista")
public class Tourist extends User {

	private String nationality;
	@OneToMany
	@JoinTable(name = "Tourist_outings", joinColumns = @JoinColumn(name = "tourist_nickname"), inverseJoinColumns = @JoinColumn(name = "out_name"))
	private List<Outing> myOutings;

	public Tourist() {};

	public Tourist(String nickname, String name, String lastName, String email, String birthday, String password,
			String nationality) {
		super(nickname, name, lastName, email, birthday, password);
		this.nationality = nationality;
		this.setMyOutings(new ArrayList<Outing>());
	}
	
	public Tourist(DataTypeTourist dtt) {
		super(dtt.getNickname(), dtt.getName(), dtt.getLastName(), dtt.getEmail(), dtt.getBirthday(), dtt.getPassword(), dtt.getImage());
		this.nationality = dtt.getNationality();
		this.setMyOutings(new ArrayList<Outing>());
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Outing> getOutings() {
		return myOutings;
	}

	public void setMyOutings(List<Outing> myOutings) {
		this.myOutings = myOutings;
	}

	public void addOuting(Outing n) throws UserException {
		if (this.myOutings.contains(n))
			throw new UserException("Ya hay una inscripcion con las mismas credenciales.");
		else
			this.myOutings.add(n);
	}

}
