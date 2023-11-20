package servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import enums.ActivityState;
import exceptions.ActivityException;
import exceptions.InscriptionException;
import exceptions.OutingException;
import exceptions.UserException;
import logic.DataTypeActivity;
import logic.DataTypeOuting;
import logic.Factory;
import presentation.IActivity;
import presentation.IInscription;

@WebServlet("/CreateInscription")
public class CreateInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IActivity iActivity;
	IInscription iInscription;

	public CreateInscription() {
		Factory factory = Factory.getInstance();
		iInscription = factory.getIInscription();
		iActivity = factory.getIActivity();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		String actName = request.getParameter("combobox-act-ins");

		DataTypeActivity[] dtas = iActivity.listTouristActivities(ActivityState.CONFIRMADA);
		request.setAttribute("activities", dtas);
		if (actName != null && !actName.isEmpty()) {
			request.setAttribute("actName", actName);
		}

		String activityName = request.getParameter("activityName");
		System.out.println("ACTNAME:" + actName);
		System.out.println("activityName:" + activityName);
		HttpSession session = request.getSession(false);
		System.out.println("metodoGET:" + request.getParameter("procesoGet"));
		String proceso = request.getParameter("procesoGet");
		if (proceso != null) {
			if (proceso.equals("buscarAct")) {
				DataTypeOuting[] outings = null;

				DataTypeActivity[] act = iActivity.listTouristActivities(ActivityState.CONFIRMADA);
				for (DataTypeActivity a : act) {
					if (a.getName().trim().equals(actName.trim())) {
						outings = iActivity.chooseActivity(a.getName());

						session.setAttribute("actCost", a.getCost());

						request.setAttribute("outings", outings);
					}

					System.out.println("Nombre de la actividad:" + a.getName());
					if (outings != null) {
						for (DataTypeOuting o : outings) {
							System.out.println("	Nombre de la salida:" + o.getName());
						}
					}
				}
			} else if (proceso.equals("registrarIns")) {
				String tourist = (String) session.getAttribute("uNickname");
				String outName = request.getParameter("combobox-out-ins");
				int tourists = Integer.parseInt(request.getParameter("insNumber"));
				String date = LocalDateTime.now().toString();
				int actCost = Integer.parseInt((String) session.getAttribute("actCost"));
				int finalCost = actCost * tourists;

				try {
					System.out.println("actName:" + activityName + "|tourist:" + tourist.toString() + "|outName:" + outName
							+ "|tourists:" + tourists + "|date:" + date + "|finalCost:" + finalCost);
					iInscription.addInscription(activityName, tourist, outName, tourists, date, finalCost);
					request.setAttribute("message", "Se ha registrado con exito la inscripción");
				} catch (ActivityException e) {
					request.setAttribute("error", e.getMessage());
				} catch (OutingException e) {
					request.setAttribute("error", e.getMessage());
				} catch (UserException e) {
					request.setAttribute("error", e.getMessage());
				} catch (InscriptionException e) {
					request.setAttribute("error", e.getMessage());
				}
			}
		}

		rd = request.getRequestDispatcher("createInscription.jsp");
		rd.forward(request, response);
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher rd;
//		String actName = request.getParameter("activityName");
//		System.out.println("ACTNAME-post:" + actName);
//		HttpSession session = request.getSession(false);
//		System.out.println("metodoGET-post:" + request.getParameter("procesoGet"));
//		String proceso = request.getParameter("procesoGet");
//		if (proceso != null) {
//			if (proceso.equals("buscarAct")) {
//				DataTypeOuting[] outings = null;
//
//				DataTypeActivity[] act = iActivity.listTouristActivities(ActivityState.CONFIRMADA);
//				for (DataTypeActivity a : act) {
//					// if (a.getName().trim().equals(actName.trim())) {
//					if (a.getName().equals("w")) {
//						outings = iActivity.chooseActivity(a.getName());
//
//						session.setAttribute("actCost", a.getCost());
//
//						request.setAttribute("outings", outings);
//					}
//
//					System.out.println("Nombre de la actividad:" + a.getName());
//					if (outings != null) {
//						for (DataTypeOuting o : outings) {
//							System.out.println("	Nombre de la salida:" + o.getName());
//						}
//					}
//				}
//			} else if (proceso.equals("registrarIns")) {
//				String tourist = (String) session.getAttribute("uNickname");
//				String outName = request.getParameter("combobox-out-ins");
//				int tourists = Integer.parseInt(request.getParameter("insNumber"));
//				String date = LocalDateTime.now().toString();
//				int actCost = Integer.parseInt((String) session.getAttribute("actCost"));
//				int finalCost = actCost * tourists;
//
//				try {
//					System.out.println("actName:" + actName + "|tourist:" + tourist.toString() + "|outName:" + outName
//							+ "|tourists:" + tourists + "|date:" + date + "|finalCost:" + finalCost);
//					iInscription.addInscription(actName, tourist, outName, tourists, date, finalCost);
//				} catch (ActivityException e) {
//					request.setAttribute("error", e.getMessage());
//				} catch (OutingException e) {
//					request.setAttribute("error", e.getMessage());
//				} catch (UserException e) {
//					request.setAttribute("error", e.getMessage());
//				} catch (InscriptionException e) {
//					request.setAttribute("error", e.getMessage());
//				}
//			}
//		}
//
//		rd = request.getRequestDispatcher("createInscription.jsp");
//		rd.forward(request, response);
//	}
}
