package logic.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import enums.ActivityState;
import exceptions.UserException;
import logic.Activity;
import logic.DataTypeActivity;
import logic.DataTypeInscription;
import logic.DataTypeOuting;
import logic.DataTypeProvider;
import logic.DataTypeTourist;
import logic.DataTypeUser;
import logic.Inscription;
import logic.Outing;
import logic.Provider;
import logic.Tourist;
import logic.User;
import logic.managment.ActivitiesManagement;
import logic.managment.UserManagement;
import presentation.IUser;

public class UserController implements IUser {

	public List<DataTypeTourist> listTourists() {
		UserManagement um = UserManagement.getinstance();
		// User[] usrs = um.getUsers();
		List<User> usrs = um.getUsersPersistence();
		List<DataTypeTourist> miD = new ArrayList<DataTypeTourist>();
		for (User usr : usrs) {
			if (usr instanceof Tourist) {
				DataTypeTourist n = new DataTypeTourist((Tourist) usr);
				miD.add(n);
			}
		}
		return miD;
	};

	public DataTypeTourist[] listArrayTourists() {
		List<DataTypeTourist> to = this.listTourists();
		DataTypeTourist[] tourist = new DataTypeTourist[to.size()];

		for (int i = 0; i < to.size(); i++) {
			tourist[i] = to.get(i);
		}

		return tourist;
	};

	public void addProvider(DataTypeProvider dt) throws UserException {
		UserManagement um = UserManagement.getinstance();
		if (um.getUserPersistence(dt.getNickname()) != null) {
			throw new UserException("El nickname ya esta en uso.");
		}

		// for (User user : um.getUsers()) {
		for (User user : um.getUsersPersistence()) {
			if (user.getEmail().equals(dt.getEmail())) {
				throw new UserException("El email ya esta en uso.");
			}
		}

		Provider nuevo = new Provider(dt);
		um.addUserPersistance(nuevo);
	}

	public void addTourist(DataTypeTourist dt) throws UserException {
		UserManagement um = UserManagement.getinstance();
		// if (um.getUser(dt.getNickname()) != null) {
		if (um.getUserPersistence(dt.getNickname()) != null) {
			throw new UserException("El nickname ya está en uso.");
		}

		// for (User user : um.getUsers()) {
		for (User user : um.getUsersPersistence()) {
			if (user.getEmail().equals(dt.getEmail())) {
				throw new UserException("El email ya está en uso.");
			}

		}
		Tourist nuevo = new Tourist(dt);
		// um.addUser((User) nuevo);
		um.addUserPersistance(nuevo);
	};

	public void listUser() {// Para la CommandLine
		UserManagement um = UserManagement.getinstance();
		User[] user = um.getUsers();
		for (int i = 0; i < user.length; i++) {
			System.out.println((i + 1) + "-" + user[i].getNickname());
		}
	}

	public List<DataTypeUser> listUsers() {
		UserManagement um = UserManagement.getinstance();
		// User[] us = um.getUsers();
		List<User> users = um.getUsersPersistence();
		List<DataTypeUser> data = new ArrayList<DataTypeUser>();
		for (User user : users) {
			if (user instanceof Provider) {
				data.add(new DataTypeProvider((Provider) user));
			} else if (user instanceof Tourist) {
				data.add(new DataTypeTourist((Tourist) user));
			}
		}
		// for(int i=0;i<us.length;i++) {
		// data.add(new DataTypeUser(us[i]));
		// }
		return data;
	}

	public DataTypeUser dataUser(String nick) {
		UserManagement um = UserManagement.getinstance();
		// User us = um.getUser(nick);
		User us = um.getUserPersistence(nick);
		if (us instanceof Tourist) {
			Tourist tu = (Tourist) us;
			DataTypeTourist data = new DataTypeTourist(tu.getNickname(), tu.getName(), tu.getLastName(), tu.getEmail(),
					tu.getBirthday(), tu.getPassword(), tu.getNationality(), tu.getImage());
			return data;
		} else {
			Provider pro = (Provider) us;
			DataTypeProvider data = new DataTypeProvider(pro.getNickname(), pro.getName(), pro.getLastName(),
					pro.getEmail(), pro.getBirthday(), pro.getPassword(), pro.getDescription(), pro.getWebSite(),
					pro.getImage());
			return data;
		}
	}

	@Override
	public List<DataTypeProvider> listProviders() {
		UserManagement um = UserManagement.getinstance();
		// User[] users = um.getUsers();
		List<User> users = um.getUsersPersistence();
		List<DataTypeProvider> dtProviders = new ArrayList<DataTypeProvider>();
		if (users != null) {
			for (User user : users) {
				if (user instanceof Provider) {
					dtProviders.add(new DataTypeProvider((Provider) user));
				}
			}
		}
		return dtProviders;
	}

	public void infoUser(int lugar) {// Para utilizar en la commandline
		UserManagement um = UserManagement.getinstance();
		User us[] = um.getUsers();
		if (us[lugar - 1] instanceof Tourist) {
			Tourist tu = (Tourist) us[lugar - 1];
			System.out.println("Nombre:" + tu.getName());
			System.out.println("Apellido:" + tu.getLastName());
			System.out.println("NickName:" + tu.getNickname());
			System.out.println("Email:" + tu.getEmail());
			System.out.println("Cumpleaños:" + tu.getBirthday());
			System.out.println("Contraseña:" + tu.getPassword());
			System.out.println("Nacionalidad:" + tu.getNationality());
		} else {
			Provider pro = (Provider) us[lugar - 1];
			System.out.println("Nombre:" + pro.getName());
			System.out.println("Apellido:" + pro.getLastName());
			System.out.println("NickName:" + pro.getNickname());
			System.out.println("Email:" + pro.getEmail());
			System.out.println("Cumpleaños:" + pro.getBirthday());
			System.out.println("Contraseña:" + pro.getPassword());
			System.out.println("Nacionalidad:" + pro.getDescription());
			System.out.println("Sitio Web:" + pro.getWebSite());
		}
	}

	public DataTypeActivity[] listProviderActivities(DataTypeProvider dtp) {
		UserManagement um = UserManagement.getinstance();

		Provider provider = (Provider) um.getUserPersistence(dtp.getNickname());
		Map<String, Activity> activitiesMap = provider.getActivitiesMap();

		int i = 0;
		DataTypeActivity[] activities = new DataTypeActivity[activitiesMap.size()];
		for (Entry<String, Activity> entry : activitiesMap.entrySet()) {
			activities[i++] = new DataTypeActivity(entry.getValue());
		}

		return activities;
	}

	public DataTypeOuting[] listTouristOutings(DataTypeTourist dtt) {
		UserManagement um = UserManagement.getinstance();

		Tourist tourist = (Tourist) um.getUserPersistence(dtt.getNickname());
		List<Outing> outingsList = tourist.getOutings();
		DataTypeOuting[] dtOutingsArray = new DataTypeOuting[outingsList.size()];
		int i = 0;
		for (Outing outing : outingsList) {
			dtOutingsArray[i++] = new DataTypeOuting(outing);
		}

		return dtOutingsArray;

	}

	public void editUser(DataTypeUser dtu) {
		UserManagement um = UserManagement.getinstance();
		um.editUser(dtu);
	}

	public List<DataTypeActivity> listProviderActivities(DataTypeProvider dtp, boolean inConfirmedState) {
		ActivitiesManagement am = ActivitiesManagement.getInstance();

		List<Activity> activities = am.getActivitiesPersistence(false);
		List<DataTypeActivity> dtas = new ArrayList<DataTypeActivity>();
		for (int i = 0; i < activities.size(); i++) {
			if (inConfirmedState) {
				// Se agregan solo las Actividades CONFIRMADAS
				if (activities.get(i).getState().equals(ActivityState.CONFIRMADA)) {
					dtas.add(new DataTypeActivity(activities.get(i)));
				}
			} else {
				// Se agregan todas las Actividades a la lista
				dtas.add(new DataTypeActivity(activities.get(i)));
			}
		}
		return dtas;
	}

	public List<DataTypeInscription> getTouristInscriptions(DataTypeTourist dtt) {
		UserManagement um = UserManagement.getinstance();
		Tourist tourist = (Tourist) um.getUserPersistence(dtt.getNickname());

		List<DataTypeInscription> touristInscriptions = new ArrayList<>();
		for (Outing outing : tourist.getOutings()) {
			List<Inscription> inscriptions = outing.getInscriptions();
			for (Inscription inscription : inscriptions) {
				if (inscription.getTourist().getNickname().equals(dtt.getNickname())) {
					DataTypeInscription dti = new DataTypeInscription(inscription.getInscriptionDate(),
							inscription.getTouristsNumber(), inscription.getTourist().getNickname(),
							inscription.getCost());
					touristInscriptions.add(dti);
				}
			}
		}

		return touristInscriptions;
	}
}