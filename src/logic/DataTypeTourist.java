package logic;

import java.util.List;


public class DataTypeTourist extends DataTypeUser {
	
	private String nationality;
	private List<Outing> myOutings;
	
	public DataTypeTourist(Tourist n) {
		super(n.getNickname(),n.getName(),n.getLastName(),n.getEmail(),n.getPassword(),n.getBirthday(), n.getImage());
		this.nationality = n.getNationality();
	}
	
	public DataTypeTourist(String nickname, String name, String lastName, String email, String birthday,
			 String password, String nationality) {
		super(nickname, name, lastName, email, password, birthday);
		this.nationality = nationality;
	}
	
	public DataTypeTourist(String nickname, String name, String lastName, String email, String birthday,
			 String password, String nationality, byte[] image) {
		super(nickname, name, lastName, email, password, birthday, image);
		this.nationality = nationality;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public List<Outing> getMyOutings() {
		return myOutings;
	}

	public void setMyOutings(List<Outing> myOutings) {
		this.myOutings = myOutings;
	}
	
	
}
