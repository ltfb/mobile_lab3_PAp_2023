package servlets;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import enums.ActivityState;
import logic.Activity;
import logic.DataTypeActivity;
import logic.DataTypeOuting;
import logic.Factory;
import logic.managment.ActivitiesManagement;
import presentation.IActivity;
import presentation.IOuting;

@WebServlet("/ConsultOuting")
public class ConsultOuting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IActivity iActivity;
	IOuting iOuting;

	public ConsultOuting() {
		Factory factory = Factory.getInstance();
		iActivity = factory.getIActivity();
		iOuting = factory.getIOuting();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;

		String actName = request.getParameter("activity");
		DataTypeActivity[] dtas = iActivity.listTouristActivities(ActivityState.CONFIRMADA);
		request.setAttribute("activities", dtas);

		if (actName != null && !actName.isEmpty()) {
			request.setAttribute("actName", actName);
			
			DataTypeActivity dta = null;
			for (DataTypeActivity item : dtas) {
				if (item.getName().trim().equals(actName.trim())) {
					dta = item;
					break;
				}
			}

			DataTypeOuting[] outings = iActivity.chooseActivity(dta.getName());
			request.setAttribute("outings", outings);
		}

		rd = request.getRequestDispatcher("consultOuting.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd;
		String actName = request.getParameter("actId");
		String outName = request.getParameter("outId");
		
		DataTypeOuting dto = null;
		if (actName == null) { // es turista
			ActivitiesManagement am = ActivitiesManagement.getInstance();
			
			List<Activity> activities = am.getActivitiesPersistence(false);
			for (Activity act : activities) {
				dto = act.getOuting(outName);
				if (dto != null) { // Encontro la salida y salgo del for
					break; 
				}
			}
		} else { // es proveedor
			dto = iOuting.consultOuting(actName, outName);
		}
		
		request.setAttribute("outing", dto);
		if (dto.getImage() != null) {
			String base64Image = Base64.getEncoder().encodeToString(dto.getImage());
			request.setAttribute("outInfoImage", base64Image);
		} else {
			System.out.println("La Salida no tiene una imagen asociada");
		}

		rd = request.getRequestDispatcher("infoOuting.jsp");
		rd.forward(request, response);
	}
}