package presentation;

import enums.ActivityState;
import exceptions.ActivityException;
import exceptions.UserException;
import logic.DataTypeActivity;
import logic.DataTypeOuting;

public interface IActivity {

	public abstract DataTypeOuting[] chooseActivity(String activity);

	public abstract void activityRegistration(String providerNickname, DataTypeActivity dtActivity)
			throws ActivityException, UserException;
	
	public abstract void confirmActivity(String actName);
	public abstract void cancel();

	public abstract DataTypeActivity[] listTouristActivities(ActivityState state);

	void rejectActivity(String name);

}