package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import logic.DataTypeActivity;
import logic.DataTypeInscription;
import logic.DataTypeOuting;
import logic.DataTypeProvider;
import logic.DataTypeTourist;
import logic.DataTypeUser;
import logic.Factory;
import presentation.IActivity;
import presentation.IInscription;
import presentation.IUser;
import utils.DataTypeOutingAdapter;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IUser iUser;
	IActivity iActivity;
	IInscription iInscription;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfile() {
		Factory factory = Factory.getInstance();
		iUser = factory.getIUser();
		iActivity = factory.getIActivity();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* ------------------BLOQUE AJAX---------------- */
		String activity = request.getParameter("activityId");
		System.out.println(activity);
		if (activity != null && !activity.isEmpty()) {
			// Aquí deberías agregar la lógica necesaria para determinar las opciones del
			// segundo ComboBox
			DataTypeOuting[] outings = iActivity.chooseActivity(activity);

			// Convierte la lista de opciones en formato JSON
			String json = parseToJson(outings);
			System.out.println(json);

			// Establece la respuesta en formato JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			/* ---------------BLOQUE AJAX---------------- */
		} else if (activity == null) {

			String userId = request.getParameter("userId"); // userId == nickname
			DataTypeUser dtu = iUser.dataUser(userId);

			request.setAttribute("userName", dtu.getName());
			request.setAttribute("userLastName", dtu.getLastName());
			request.setAttribute("userNickname", dtu.getNickname());
			request.setAttribute("userEmail", dtu.getEmail());
			request.setAttribute("userBirthday", dtu.getBirthday());
			request.setAttribute("userPassword", dtu.getPassword());
			System.out.println(dtu.getImage());
			if (dtu.getImage() != null) {
				request.setAttribute("userImage", Base64.getEncoder().encodeToString(dtu.getImage()));
			}
			HttpSession session = request.getSession();
			String sessionNickname = (String) session.getAttribute("uNickname");
			if (dtu instanceof DataTypeProvider) {
				DataTypeProvider dtp = (DataTypeProvider) dtu;
				request.setAttribute("userDescription", dtp.getDescription());
				request.setAttribute("userWebsite", dtp.getWebSite());
				request.setAttribute("userType", "Proveedor"); // Se muestra exactamente el valor "Provider" en la web

				List<DataTypeActivity> dtas = new ArrayList<DataTypeActivity>();
				if (sessionNickname != null && sessionNickname.trim().equals(dtp.getNickname())) {
					dtas = iUser.listProviderActivities(dtp, false);
				} else {
					dtas = iUser.listProviderActivities(dtp, true);
				}
				request.setAttribute("activities", dtas);
			} else if (dtu instanceof DataTypeTourist) {
				DataTypeTourist dtt = (DataTypeTourist) dtu;
				request.setAttribute("userNationality", dtt.getNationality());
				request.setAttribute("userType", "Turista"); // Se muestra exactamente el valor "Turista" en la web
				request.setAttribute("touristOutings", iUser.listTouristOutings(dtt));
				
				Map<String, DataTypeInscription> dtis = new HashMap<String, DataTypeInscription>();
				if (sessionNickname != null && sessionNickname.trim().equals(dtt.getNickname())) {
					for (DataTypeOuting dto : iUser.listTouristOutings(dtt)) {
						for (DataTypeInscription dti : dto.getInscriptions()) {
							if (dti.getTouristNickname().equals(dtt.getNickname())) {
								// esto funciona gracias a que un turista no puede tener mas de una inscription
								// para una salida
								dtis.put(dto.getName(), dti);
							}
						}
					}
				}
				request.setAttribute("inscriptions", dtis);
			}

			request.getRequestDispatcher("userProfile.jsp").forward(request, response);
		}
	}

	// Define un método para convertir una lista de salidas en formato JSON.
	public static String parseToJson(DataTypeOuting[] outings) {
		// Crea una instancia de la clase Gson.
		Gson gson = new GsonBuilder().registerTypeAdapter(DataTypeOuting.class, new DataTypeOutingAdapter()).create();
		// Convierte el array de objetos DataTypeOuting en una cadena JSON.
		String json = gson.toJson(outings);
		return json;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
