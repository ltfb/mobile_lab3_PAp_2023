package logic.controllers;

import java.util.List;

import enums.ActivityState;
import exceptions.ActivityException;
import exceptions.UserException;
import logic.Activity;
import logic.DataTypeActivity;
import logic.DataTypeOuting;
import logic.Provider;
import logic.managment.ActivitiesManagement;
import logic.managment.UserManagement;
import presentation.IActivity;

public class ActivityController implements IActivity {
	@Override
	public DataTypeActivity[] listTouristActivities(ActivityState state) {
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		List<Activity> activities = am.getActivitiesPersistence(state);
		DataTypeActivity[] dtActivities = new DataTypeActivity[activities.size()];
		for (int i = 0; i < activities.size(); i++) {
			dtActivities[i] = new DataTypeActivity(activities.get(i));
		}
		return dtActivities;
	}
	
	@Override
	public DataTypeOuting[] chooseActivity(String activityName) {
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		// Activity activity = am.getActivity(activityName);
		Activity activity = am.getActivityPersistence(activityName);
		return activity.getOutings();
	}

	@Override
	public void activityRegistration(String providerNickname, DataTypeActivity dtActivity)
			throws ActivityException, UserException {
		UserManagement um = UserManagement.getinstance();
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		try {
//			Provider provider = (Provider) um.getUser(providerNickname);
			Provider provider = (Provider) um.getUserPersistence(providerNickname);
			if (am.getActivityPersistence(dtActivity.getName()) != null) {
				throw new ActivityException("El nombre de la actividad: \"" + dtActivity.getName() + "\" ya existe.");
			}
			Activity activity = new Activity(dtActivity.getName(), dtActivity.getDescription(),
					dtActivity.getDuration(), dtActivity.getCost(), dtActivity.getCity(), dtActivity.getEntryDate(),
					dtActivity.getImage());

			// Agrega nueva Actividad a la coleccion de actividades
//			am.addActivity(activity);
			am.addActivityPersistence(provider, activity);
		} catch (ClassCastException cce) {
			throw new UserException("El proveedor de nickname: \"" + providerNickname + "\" no existe.");
		}
	}

	@Override
	public void cancel() {
		// Este metodo solo sirve de guia para el caso de uso. En caso de sea necesario,
		// implementar este metodo.
	}

	@Override
	public void confirmActivity(String name) {
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		Activity activity = am.getActivityPersistence(name);
		activity.setState(ActivityState.CONFIRMADA);
		am.updateActivityPersistence(activity);
	}

	@Override
	public void rejectActivity(String name) {
		ActivitiesManagement am = ActivitiesManagement.getInstance();
		Activity activity = am.getActivityPersistence(name);
		activity.setState(ActivityState.RECHAZADA);
		am.updateActivityPersistence(activity);
	}
}