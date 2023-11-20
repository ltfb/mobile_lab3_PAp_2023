package servlets;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import enums.ActivityState;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import publish.DataTypeActivity;
import publish.DataTypeActivityArray;
import publish.DataTypeOutingArray;

@WebServlet("/ConsultActivity")
public class ConsultActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConsultActivity() {
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		publish.WebServicesService service = new publish.WebServicesService();
		publish.WebServices port = service.getWebServicesPort();
		DataTypeActivityArray dtas = port.listTouristActivities(ActivityState.CONFIRMADA);

		RequestDispatcher rd;
		request.setAttribute("activities", dtas.getActivities());
		System.out.println(dtas.getActivities().toString());

		String actName = request.getParameter("act-combobox");
		System.out.println(actName);
		if (actName != null) {
			DataTypeActivity dta = null;
			for (DataTypeActivity item : dtas.getActivities()) {
				if (item.getName().trim().equals(actName.trim())) {
					dta = item;
					break;
				}
			}

			request.setAttribute("consultActName", dta.getName());
			request.setAttribute("consultActCost", dta.getCost());
			request.setAttribute("consultActDesc", dta.getDescription());
			request.setAttribute("consultActState", dta.getState());
			request.setAttribute("consultActDuration", dta.getDuration());
			String formattedDate;
			if (dta.getEntryDate() != null) {
				formattedDate = dta.getEntryDate().formatted(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			} else {
				formattedDate = "No se encontró fecha";
			}
			request.setAttribute("consultActDate", formattedDate);

			if (dta.getImage() != null) {
				System.out.println("getImage en ConsultActivity:" + dta.getImage());
				String base64Image = Base64.getEncoder().encodeToString(dta.getImage());
				request.setAttribute("consultActImage", base64Image);
			} else {
				System.out.println("La actividad no tiene una imagen asociada");
			}

			DataTypeOutingArray outings = port.chooseActivity(dta.getName());
			System.out.println("Salidas:" + outings.getOutings().toString());
			request.setAttribute("outings", outings.getOutings());
		}

		rd = request.getRequestDispatcher("consultActivity.jsp");
		rd.forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}
}