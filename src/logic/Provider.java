package logic;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("proveedor")
public class Provider extends User {

	private String description;
	private String webSite;
	private Map<String, Activity> activitiesMap;

	public Provider() {
	};

	public Provider(String nickname, String name, String lastName, String email, String birthday, String password,
			String description, String webSite) {
		super(nickname, name, lastName, email, birthday, password);
		this.description = description;
		this.webSite = webSite;
		this.activitiesMap = new HashMap<String, Activity>();
	}
	
	public Provider(DataTypeProvider dtp) {
		super(dtp.getNickname(), dtp.getName(), dtp.getLastName(), dtp.getEmail(), dtp.getBirthday(), dtp.getPassword(), dtp.getImage());
		this.description = dtp.getDescription();
		this.webSite = dtp.getWebSite();
		this.activitiesMap = new HashMap<String, Activity>();
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

	public Map<String, Activity> getActivitiesMap() {
		return activitiesMap;
	}

	public void setActivitiesMap(Map<String, Activity> activitiesMap) {
		this.activitiesMap = activitiesMap;
	}

}
