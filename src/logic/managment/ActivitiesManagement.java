package logic.managment;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.ActivityState;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import logic.Activity;
import logic.Outing;
import logic.Provider;

public class ActivitiesManagement {

	private Map<String, Activity> activitiesMap = new HashMap<String, Activity>();
	private static ActivitiesManagement instance;

	private ActivitiesManagement() {
		activitiesMap = new HashMap<String, Activity>();
	}

	public static ActivitiesManagement getInstance() {
		if (instance == null)
			instance = new ActivitiesManagement(); // Constructor solo se llama de aca
		return instance;
	}

	public void addActivity(Activity act) {
		String name = act.getName(); // Get de la cedula
		activitiesMap.put(name, act); // agrego al usuario a la coleccion
	}

	public void addActivityPersistence(Provider provider, Activity act) {
		// Linkea actividad a proveedor
		provider.getActivitiesMap().put(act.getName(), act);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(provider);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}

	public Activity getActivity(String name) {
		return activitiesMap.get(name);
	}

	public Activity getActivityPersistence(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();

		Activity activity = em.find(Activity.class, name);

		em.close();
		emf.close();
		return activity;
	}

	public Activity[] getActivities() { // Devuelve la coleccion completa de los usuarios en array
		if (activitiesMap.isEmpty())
			return new Activity[0];
		else {
			Collection<Activity> act = activitiesMap.values(); // Metodo values devuelve la coleccion entera
			Object[] o = act.toArray(); // Devuelve los objetos a una array
			Activity[] activities = new Activity[o.length]; // Creo un array de usuarios
			for (int i = 0; i < o.length; i++) {
				activities[i] = (Activity) o[i]; // Cargo con la salida de toArray
			}

			return activities;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Activity> getActivitiesPersistence(boolean inAddedState) {
		// Memoria en persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();
		Query query;
		if (inAddedState) {
			query = em.createQuery("SELECT a FROM Activity a WHERE a.state = :addedState");
			query.setParameter("addedState", ActivityState.AGREGADA);
		} else {
			query = em.createQuery("SELECT a FROM Activity a");
		}
		List<Activity> activities = query.getResultList();
		em.close();
		emf.close();
		return activities;
	}

	@SuppressWarnings("unchecked")
	public List<Activity> getActivitiesPersistence(ActivityState state) {
		// Memoria en persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();
		Query query;

		query = em.createQuery("SELECT a FROM Activity a WHERE a.state = :actState");
		switch (state) {
		case AGREGADA:
			query.setParameter("actState", ActivityState.AGREGADA);
			break;
		case CONFIRMADA:
			query.setParameter("actState", ActivityState.CONFIRMADA);
			break;
		case RECHAZADA:
			query.setParameter("actState", ActivityState.RECHAZADA);
			break;
		}
		
		List<Activity> activities = query.getResultList();
		em.close();
		emf.close();
		return activities;
	}

	public Activity addOutingToActivity(String activityName, Outing outing) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();

		Activity activity = em.find(Activity.class, activityName);
		activity.addOuting(outing);

		em.getTransaction().begin();
		em.persist(outing);
		em.persist(activity);
		em.getTransaction().commit();

		em.close();
		emf.close();
		return activity;
	}

	public void updateActivityPersistence(Activity activity) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(activity);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}
}