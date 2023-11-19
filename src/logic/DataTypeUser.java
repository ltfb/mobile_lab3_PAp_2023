package logic;

public class DataTypeUser {
	private String nickname;
	private String name;
	private String lastName;
	private String email;
	private String birthday; // TODO: podemos crear un DataType para birthday
	private String password;
	private byte[] image;
	
	public DataTypeUser(String nickname, String name, String lastName, String email, String password, String birthday) {
		this.nickname = nickname;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.password = password;
	}

	public DataTypeUser(String nickname, String name, String lastName, String email, String password, String birthday, byte[] image) {
		this.nickname = nickname;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.password = password;
		this.image = image;
	}

	public DataTypeUser(User us) {
		this.nickname = us.getNickname();
		this.name = us.getName();
		this.lastName = us.getLastName();
		this.email = us.getEmail();
		this.birthday = us.getBirthday();
		this.password = us.getPassword();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return getNickname();
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

}
