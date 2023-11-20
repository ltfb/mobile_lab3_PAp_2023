package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import exceptions.ActivityException;
import exceptions.UserException;
import logic.DataTypeActivity;
import logic.Factory;
import presentation.IActivity;

/**
 * Servlet implementation class CreateActivity
 */
@WebServlet("/CreateActivity")
@MultipartConfig
public class CreateActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IActivity iAct;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateActivity() {
		Factory factory = Factory.getInstance();
		iAct = factory.getIActivity();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;

		HttpSession session = request.getSession(false);
		String provider = (String) session.getAttribute("uNickname");
		String name = request.getParameter("actName");
		String desc = request.getParameter("actDesc");
		String duration = request.getParameter("actDur");
		String cost = request.getParameter("actCost");
		String city = request.getParameter("actCity");

		Part img = request.getPart("actImg");
		byte[] imageInBytes = null;
		if (img != null) {
			System.out.println("imgName:" + img.getName());
			System.out.println("imgSize:" + img.getSize());
			System.out.println("imgContentType:" + img.getContentType());

			InputStream inputStream = img.getInputStream();

			imageInBytes = new byte[(int) img.getSize()];
			inputStream.read(imageInBytes);
			inputStream.close();
		}
		System.out.println("inbytes:" + imageInBytes);
		LocalDateTime date = LocalDateTime.now();
		String camposVacios = checkForm(name, desc, duration, cost, city);
		if (camposVacios.isEmpty()) {
			try {
				iAct.activityRegistration(provider, new DataTypeActivity(name, desc, Integer.parseInt(duration), cost, city, date, imageInBytes));

				request.setAttribute("message", "Se ha ingresado con exito la actividad " + name);
				System.out.println("Se ha ingresado con exito la actividad " + name);
			} catch (UserException ue) {
				request.setAttribute("error", ue.getMessage());
			} catch (ActivityException ae) {
				request.setAttribute("error", ae.getMessage());
			}
		} else {
			request.setAttribute("error", camposVacios);
		}

		rd = request.getRequestDispatcher("createActivity.jsp");
		rd.forward(request, response);
	}

	protected String checkForm(String name, String desc, String duration, String cost, String city) {
		if (name.isEmpty()) {
			return "El nombre no puede ser vacío";
		}
		if (desc.isEmpty()) {
			return "La descripción no puede ser vacía";
		}
		if (duration.isEmpty()) {
			return "La duración no puede ser vacía";
		}
		if (cost.isEmpty()) {
			return "El costo no puede ser vacío";
		}
		if (city.isEmpty()) {
			return "La ciudad no puede ser vacía";
		}
		return "";
	}
}
