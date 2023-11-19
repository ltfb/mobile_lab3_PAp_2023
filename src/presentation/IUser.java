package presentation;

import java.util.List;

import exceptions.UserException;
import logic.DataTypeActivity;
import logic.DataTypeInscription;
import logic.DataTypeOuting;
import logic.DataTypeProvider;
import logic.DataTypeTourist;
import logic.DataTypeUser;

public interface IUser {

	public abstract List<DataTypeTourist> listTourists();

	public abstract DataTypeTourist[] listArrayTourists();

	public abstract void addProvider(DataTypeProvider dt) throws UserException;

	public abstract void addTourist(DataTypeTourist dt) throws UserException;

	public abstract List<DataTypeProvider> listProviders();

	public abstract List<DataTypeUser> listUsers();

	public abstract DataTypeUser dataUser(String nick);

	public abstract DataTypeActivity[] listProviderActivities(DataTypeProvider dtp);

	public abstract DataTypeOuting[] listTouristOutings(DataTypeTourist dtt);
	
	public abstract void editUser(DataTypeUser dtu);
	
	public abstract List<DataTypeActivity> listProviderActivities(DataTypeProvider dtp, boolean isConfirmedState);
	
	public abstract List<DataTypeInscription> getTouristInscriptions(DataTypeTourist dtt);
	
}
