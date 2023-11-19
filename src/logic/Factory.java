package logic;

import logic.controllers.ActivityController;
import logic.controllers.InscriptionController;
import logic.controllers.OutingController;
import logic.controllers.UserController;
import presentation.IActivity;
import presentation.IInscription;
import presentation.IOuting;
import presentation.IUser;

public class Factory {

	static Factory instance = null;

	public static Factory getInstance() {
		if (instance == null) {
			instance = new Factory(); // Solo de aca se puede llamar al contructor
		}
		return instance;
	}

	public IUser getIUser() {
		return new UserController();
	}

	public IActivity getIActivity() {
		return new ActivityController();
	}

	public IOuting getIOuting() {
		return new OutingController();
	}

	public IInscription getIInscription() {
		return new InscriptionController();
	}

}
