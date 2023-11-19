package presentation;

import exceptions.ActivityException;
import exceptions.OutingException;
import logic.DataTypeInscription;
import logic.DataTypeOuting;

public interface IOuting {

	public abstract DataTypeOuting[] getOutings(String activityName);

	public abstract DataTypeOuting consultOuting(String activityName, String outingName);

	public abstract void outingRegistration(String activityName, DataTypeOuting dtOuting) throws OutingException, ActivityException;

	public abstract void cancel();

	public abstract DataTypeInscription[] chooseOuting(String activityName, String outing);
}
