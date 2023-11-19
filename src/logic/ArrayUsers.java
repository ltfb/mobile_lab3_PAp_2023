package logic;

public class ArrayUsers {
	private DataTypeUser[] Users;
	
	public ArrayUsers() {};
	
	public ArrayUsers(DataTypeUser[] usr) {
		this.Users = usr;
	};
	
	public DataTypeUser[] getOutings() {
		return this.Users;
	}

	public void setOutings(DataTypeUser[] usr) {
		this.Users = usr;
	}
	
}