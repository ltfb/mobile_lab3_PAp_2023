package presentation;

import exceptions.ActivityException;
import exceptions.InscriptionException;
import exceptions.OutingException;
import exceptions.UserException;

public interface IInscription {
	
	public void addInscription(String activityName, String touristMail, String outingName,int count,String date,int cost) throws ActivityException, OutingException, UserException, InscriptionException;
	
}
