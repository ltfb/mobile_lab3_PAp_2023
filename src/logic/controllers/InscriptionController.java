package logic.controllers;

import exceptions.ActivityException;
import exceptions.InscriptionException;
import exceptions.OutingException;
import exceptions.UserException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logic.Activity;
import logic.Inscription;
import logic.Outing;
import logic.Tourist;
import logic.managment.ActivitiesManagement;
import logic.managment.UserManagement;
import presentation.IInscription;

public class InscriptionController implements IInscription {

	public void addInscription(String activityName, String touristNickname, String outingName, int touristNumber,
			String date, int cost) throws ActivityException, OutingException, UserException, InscriptionException {
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		UserManagement um = UserManagement.getinstance();

		Activity miAct = am.getActivityPersistence(activityName);
		Tourist tourist = (Tourist) um.getUserPersistence(touristNickname);
		if (tourist == null) {
			throw new UserException("El mail del usuario: \"" + touristNickname + "\" no existe.");
		}
		if (miAct == null) {
			throw new ActivityException("El nombre de la actividad: \"" + activityName + "\" no existe.");
		}
		if (miAct.getOutingMap().get(outingName) == null) {
			throw new OutingException("El nombre de la salida: \"" + outingName + "\" no existe.");
		}

		Outing miOut = miAct.getOutingMap().get(outingName);

		if (miOut.getFreePlaces() < touristNumber) {
			throw new OutingException("La capacidad de la salida no abarca la cantidad inscripciones solicitadas: "
					+ touristNumber + ". Lugares disponibles: " + miOut.getFreePlaces());
		}

		for (Inscription ins : miOut.getInscriptions()) {
			if (ins.getTourist().getNickname().equals(touristNickname)) {
				throw new InscriptionException("El turista que ha elegido ya tiene una inscripcion hecha.");
			}
		}

		Inscription miIns = new Inscription(date, touristNumber, cost, tourist);
		miOut.addInscription(miIns);

		tourist.addOuting(miOut);
		for (Outing o : tourist.getOutings()) {
			System.out.println("DEBUG nombre de salida:"+o.getNombre());
		}

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(tourist);
		em.merge(miOut);
		em.merge(miAct);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}}
