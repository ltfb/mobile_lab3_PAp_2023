package logic.managment;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import exceptions.UserException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import logic.DataTypeProvider;
import logic.DataTypeTourist;
import logic.DataTypeUser;
import logic.Outing;
import logic.Provider;
import logic.Tourist;
import logic.User;

public class UserManagement {
	private Map<String, User> usersNickname;
	private static UserManagement instance;

	private UserManagement() {
//        this.usersNickname = new HashMap<String, User>();
	}

	public static UserManagement getinstance() {
		if (instance == null)
			instance = new UserManagement(); // Constructor solo se llama de acá
		return instance;
	}

	public void addUser(User usu) {
		String nick = usu.getNickname();
		usersNickname.put(nick, usu); // agrego al usuario a la coleccion
	}

	public void addUserPersistance(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}

	public User getUser(String nick) {
		return ((User) usersNickname.get(nick));
	}

	public User getUserPersistence(String nickname) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();

		User user = em.find(User.class, nickname);

		em.close();
		emf.close();

		return user;
	}

	public User[] getUsers() { // Devuelve la coleccion completa de los usuarios en array
		if (usersNickname.isEmpty())
			return new User[0];
		else {
			Collection<User> usrs = usersNickname.values(); // Metodo values devuelve la coleccion entera
			Object[] o = usrs.toArray(); // Devuelve los objetos a una array
			User[] users = new User[o.length]; // Creo un array de users
			for (int i = 0; i < o.length; i++) {
				users[i] = (User) o[i]; // Cargo con la salida de toArray
			}

			return users;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersPersistence() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("SELECT u FROM User u");
		List<User> users = query.getResultList();

		em.close();
		emf.close();
		
		return users;
	}

	public void addOutingTourist(Tourist miUs, Outing miOut) throws UserException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		miUs.addOuting(miOut);
		em.merge(miUs);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}
	
	public void editUser(DataTypeUser dtu) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		User user = em.find(User.class, dtu.getNickname());
		
		user.setName(dtu.getName());
		user.setLastName(dtu.getLastName());
		user.setBirthday(dtu.getBirthday());
		user.setPassword(dtu.getPassword());
		user.setImage(dtu.getImage());
		if (dtu instanceof DataTypeProvider) {
			DataTypeProvider dtp = (DataTypeProvider) dtu;
			Provider provider = (Provider) user;
			provider.setDescription(dtp.getDescription());
			provider.setWebSite(dtp.getWebSite());
			
			em.merge(provider);
			em.getTransaction().commit();
		} else if (dtu instanceof DataTypeTourist) {
			DataTypeTourist dtt = (DataTypeTourist) dtu;
			Tourist tourist = (Tourist) user;
			tourist.setNationality(dtt.getNationality());
			
			em.merge(tourist);
			em.getTransaction().commit();
		}
		
		em.close();
		emf.close();
	}
}
