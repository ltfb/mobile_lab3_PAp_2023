package logic;

public class DataTypeProvider extends DataTypeUser {
	private String description;
	private String webSite;

	public DataTypeProvider(String nickname, String name, String lastName, String email, String birthday,
			String password, String description, String webSite) {
		super(nickname, name, lastName, email, password, birthday);
		this.description = description;
		this.webSite = webSite;
	}

	public DataTypeProvider(String nickname, String name, String lastName, String email, String birthday,
			String password, String description, String webSite, byte[] image) {
		super(nickname, name, lastName, email, password, birthday, image);
		this.description = description;
		this.webSite = webSite;
	}

	public DataTypeProvider(Provider provider) {
		super(provider.getNickname(), provider.getName(), provider.getLastName(), provider.getEmail(), provider.getPassword(), provider.getBirthday(), provider.getImage());
		this.description = provider.getDescription();
		this.webSite = provider.getWebSite();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	@Override
	public String toString() {
		return this.getNickname();
	}

}