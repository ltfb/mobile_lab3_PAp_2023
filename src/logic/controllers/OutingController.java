package logic.controllers;

import java.util.List;

import exceptions.ActivityException;
import exceptions.OutingException;
import logic.Activity;
import logic.DataTypeInscription;
import logic.DataTypeOuting;
import logic.Inscription;
import logic.Outing;
import logic.managment.ActivitiesManagement;
import presentation.IOuting;

public class OutingController implements IOuting {

	@Override
	public DataTypeOuting[] getOutings(String activityName) {
		// Memoria en tiempo de ejecucion
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		// Activity myAct = am.getActivity(activityName);
		Activity myAct = am.getActivityPersistence(activityName);
		DataTypeOuting[] outings = myAct.getOutings();
		return outings;

	}

	@Override
	public DataTypeOuting consultOuting(String activityName, String outingName) {
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		// Activity myAct = am.getActivity(activityName);
		Activity myAct = am.getActivityPersistence(activityName);
		DataTypeOuting outing = myAct.getOuting(outingName);
		return outing;
	}

	@Override
	public void outingRegistration(String activityName, DataTypeOuting dtOuting)
			throws OutingException, ActivityException {
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		// Activity activity = am.getActivity(activityName);

		if (am.getActivityPersistence(activityName) == null) {
			throw new ActivityException("El nombre de la actividad: \"" + activityName + "\" no existe.");
		}

		List<Activity> activites = am.getActivitiesPersistence(false);
		for (Activity activity : activites) {
			if (activity.getOutingMap().get(dtOuting.getName()) != null) {
				throw new OutingException("El nombre de la salida: \"" + dtOuting.getName() + "\" ya existe.");
			}
		}

//		activity.addOuting(new Outing(dtOuting.getName(), dtOuting.getDate(), dtOuting.getHour(),
//				dtOuting.getMaxTourist(), dtOuting.getPlace(), null));
		am.addOutingToActivity(activityName, new Outing(dtOuting.getName(), dtOuting.getDate(), dtOuting.getHour(),
				dtOuting.getMaxTourist(), dtOuting.getPlace(), dtOuting.getImage()));

	}

	@Override
	public void cancel() {
		// Este metodo solo sirve de guia para el caso de uso. En caso de sea necesario,
		// implementar este metodo.
	}

	@Override
	public DataTypeInscription[] chooseOuting(String activityName, String outing) {
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		// Activity activity = am.getActivity(activityName);
		Activity activity = am.getActivityPersistence(activityName);

		int i = 0;
		DataTypeInscription[] dti = new DataTypeInscription[activity.getOutingMap().get(outing).getInscriptions()
				.size()];
		for (Inscription ins : activity.getOutingMap().get(outing).getInscriptions()) {
			dti[i++] = new DataTypeInscription(ins.getInscriptionDate(), ins.getTouristsNumber(),
					ins.getTourist().getNickname(), ins.getCost());
		}
		return dti;
	}

}
