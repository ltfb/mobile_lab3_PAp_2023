package presentation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import enums.ActivityState;
import exceptions.ActivityException;
import exceptions.OutingException;
import exceptions.UserException;
import logic.DataTypeActivity;
import logic.DataTypeInscription;
import logic.DataTypeOuting;
import logic.DataTypeProvider;
import logic.DataTypeTourist;
import logic.Factory;

public class CommandLine {
	private static IActivity IAct;
	// private static IInscription IIns;
	private static IUser IUsr;
	private static IOuting IOut;
	// private static IUserController IUC;

	static Scanner input;

	public static void main(String[] args) throws UserException {
		Factory factory = Factory.getInstance();
		IAct = factory.getIActivity();
		// IIns = factory.getIInscription();
		IUsr = factory.getIUser();
		IOut = factory.getIOuting();

		input = new Scanner(System.in);
		menu();
		int opc = Integer.parseInt(input.nextLine());

		while (opc != 0) {
			switch (opc) {
				case 1:
					userRegistration();
					break;
				case 2:
					userConsultation();
					break;
				case 4:
					touristicActivityRegistration();
					break;
				case 5:
					touristicActivityConsultation();
					break;
				case 6:
					touristicOutingRegistration();
					break;
				case 7:
					touristicOutingConsultation();
					break;
				case 8:
					touristicOutingInscription();
					break;
				case 9:
					touristicInscriptionConsultation();
					break;
				default:
					System.out.println("Por favor, elija un número dentro del rango");
					break;
			}
			menu();
			if(input.hasNextLine()) {
				opc = Integer.parseInt(input.nextLine());
			}else
				System.out.println("No hay una linea disponible");
		}
		input.close();
	}

	static void menu() {
		System.out.println("1. Alta de Usuario");
		System.out.println("2. Consulta de Usuario");
		// System.out.println("3. Alta de Usuario"); //OPCIONAL
		System.out.println("4. Alta de Actividad Turística");
		System.out.println("5. Consulta de Actividad Turística");
		System.out.println("6. Alta de Salida Turística");
		System.out.println("7. Consulta de Salida Turística");
		System.out.println("8. Inscripción a Salida Turística");
		System.out.println("9. Consulta de Inscripciones Turística");
		// System.out.println("10. Modificar Actividad Turística"); //OPCIONAL
		// System.out.println("11. Ranking de Actividad Turística"); //OPCIONAL
		System.out.println("0. Salir");
		System.out.println("Ingrese una opción:");
	}

	static void userRegistration() throws UserException {
		String nickName, name, lname, email, birthday, passwd;

		System.out.println("Ingrese 1 si quiere registrar un Turista, 2 si quiere registrar un Proveedor:");
		int op = Integer.parseInt(input.nextLine());
		if (op == 1) {
			String nationality;

			System.out.println("Ingrese el Nickname:");
			nickName = input.nextLine();
			System.out.println("Ingrese el Nombre:");
			name = input.nextLine();
			System.out.println("Ingrese el Apellido:");
			lname = input.nextLine();
			System.out.println("Ingrese el Email:");
			email = input.nextLine();
			System.out.println("Ingrese la Fecha de Nacimiento:");
			birthday = input.nextLine();
			System.out.println("Ingrese la Contraseña:");
			passwd = input.nextLine();
			System.out.println("Ingrese la Nacionalidad:");
			nationality = input.nextLine();

			DataTypeTourist nuevo = new DataTypeTourist(nickName, name, lname, email, birthday, passwd,
					nationality);
			IUsr.addTourist(nuevo);

		} else if (op == 2) {
			String dsc, link;

			System.out.println("Ingrese el Nickname:");
			nickName = input.nextLine();
			System.out.println("Ingrese el Nombre:");
			name = input.nextLine();
			System.out.println("Ingrese el Apellido:");
			lname = input.nextLine();
			System.out.println("Ingrese el Email:");
			email = input.nextLine();
			System.out.println("Ingrese la Fecha de Nacimiento:");
			birthday = input.nextLine();
			System.out.println("Ingrese la Contraseña:");
			passwd = input.nextLine();
			System.out.println("Ingrese una Descripción(opcional):");
			dsc = input.nextLine();
			System.out.println("Ingrese el link de un WebSite:");
			link = input.nextLine();

			DataTypeProvider nuevo = new DataTypeProvider(nickName, name, lname, email, birthday, passwd, dsc,
					link);

			IUsr.addProvider(nuevo);
		}
	}

	static void userConsultation() {
		// TODO: implementar este metodo
	}

	static void touristicActivityRegistration() {
		System.out.println("---Alta de Actividad Turistica---");

		boolean retry = true;
		while (retry) {

			// listando proveedores
			System.out.println("Proveedores: ");
			List<DataTypeProvider> providers = IUsr.listProviders();
			if (providers.size() == 0) {
				System.out.println("No hay proveedores disponibles");
				break;
			}
			for (DataTypeProvider dtp : providers) {
				System.out.println("- " + dtp.toString());
			}

			System.out.println("Escriba el Nickname del proveedor que desea elegir: ");
			String providerNickname = null;
			providerNickname = input.nextLine();

			System.out.println("Ingrese los datos de la Actividad que desee dar de alta");

			System.out.println("Nombre: ");
			String activityName = null;
			activityName = input.nextLine();

			System.out.println("Descripcion: ");
			String description = null;
			description = input.nextLine();

			System.out.println("Duracion: ");
			Integer duration = null;
			duration = Integer.parseInt(input.nextLine());

			System.out.println("Costo: ");
			String cost = null;
			cost = input.nextLine();

			System.out.println("Ciudad: ");
			String city = null;
			city = input.nextLine();

			LocalDateTime entryDate = LocalDateTime.now();

//			DataTypeActivity dta = new DataTypeActivity(activityName, description, duration, cost, city, entryDate, null);
//			try {
//				IAct.activityRegistration(providerNickname, dta);
//				System.out.println("Alta de Actividad exitosa!");
//				retry = false;
//			} catch (ActivityException | UserException e) {
//				System.out.println(e.getMessage());
//
//				System.out.println("Desea volver a intentarlo? Si|No");
//				String answer = input.nextLine();
//				switch (answer.toLowerCase()) {
//					case "si":
//						retry = true;
//						break;
//					case "no":
//						retry = false;
//						break;
//				}
//			}
		}
	}

	static void touristicActivityConsultation() {

		// Hay que crear las actividades y las salidas primero
		DataTypeActivity[] activities = IAct.listTouristActivities(ActivityState.CONFIRMADA);
		for (int i = 0; i < activities.length; i++) {
			System.out.println(activities[i].getName());
		}
		if (activities.length > 0) {
			System.out.println("Ingrese el nombre de la actividad que desea visualizar:");
			String activity = input.nextLine();
			DataTypeOuting[] outings = IAct.chooseActivity(activity);
			if (outings.length > 0) {
				for (int i = 0; i < outings.length; i++) {
					System.out.println(outings[i].getName());
				}
			} else {
				System.out.println("No hay salidas disponibles");
			}
		} else {
			System.out.println("No hay actividades disponibles");
		}
	}

	static void touristicOutingRegistration() {
		System.out.println("---Alta de Salida Turistica---");

		boolean retry = true;
		while (retry) {

			// listando actividades
			System.out.println("Actividades Turisticas disponibles: ");
			DataTypeActivity[] activities = IAct.listTouristActivities(ActivityState.CONFIRMADA);
			if (activities.length == 0) {
				System.out.println("No hay actividades disponibles");
				break;
			}
			for (DataTypeActivity dta : activities) {
				System.out.println("- " + dta.toString());
			}

			System.out.println("Escriba el nombre de la actividad que desea elegir: ");
			String activityName = null;
			activityName = input.nextLine();

			System.out.println("Ingrese los datos de la salida que desee dar de alta");

			System.out.println("Nombre: ");
			String outingName = null;
			outingName = input.nextLine();

			System.out.println("Fecha: ");
			String date = null;
			date = input.nextLine();

			System.out.println("Hora: ");
			String hour = null;
			hour = input.nextLine();

			System.out.println("Lugar: ");
			String place = null;
			place = input.nextLine();

			System.out.println("Cantidad de turistas: ");
			Integer touristNumber = null;
			touristNumber = Integer.parseInt(input.nextLine());

			LocalDateTime entryDate = LocalDateTime.now();

			try {
				DataTypeOuting dto = new DataTypeOuting(outingName, date, hour, touristNumber, place, entryDate);
				IOut.outingRegistration(activityName, dto);
				System.out.println("Alta de Salida exitosa!");
				
				retry = false;
			} catch (ActivityException | OutingException e) {
				System.out.println(e.getMessage());

				System.out.println("Desea volver a intentarlo? Si|No");
				String answer = input.nextLine();
				switch (answer.toLowerCase()) {
					case "si":
						retry = true;
						break;
					case "no":
						retry = false;
						break;
				}
			}
		}
	}

	static void touristicOutingConsultation() {
		// TODO: implementar este metodo
	}

	static void touristicOutingInscription() {
		// TODO: implementar este metodo
	}

	static void touristicInscriptionConsultation() {
		System.out.println("Consulta de Inscripciones Turística");

		// listando actividades
		DataTypeActivity[] activites = IAct.listTouristActivities(ActivityState.CONFIRMADA);
		for (DataTypeActivity dta : activites) {
			System.out.println(dta.toString());
		}

		System.out.println("Escriba el nombre de la actividad que desea elegir: ");
		String activityName = null;
		activityName = input.nextLine();

		// listando salidas
		DataTypeOuting[] outings = IAct.chooseActivity(activityName);
		if (outings.length > 0) {
			for (DataTypeOuting dto : outings) {
				System.out.println(dto.toString());
			}

			System.out.println("Escriba el nombre de la salida que desea elegir: ");
			String outingName = null;
			outingName = input.nextLine();

			// listando inscripciones
			DataTypeInscription[] inscriptions = IOut.chooseOuting(activityName, outingName);
			for (DataTypeInscription dti : inscriptions) {
				System.out.println(dti.toString());
			}
		} else {
			System.out.println("No hay salidas para la actividad: " + activityName);
		}

	}

}