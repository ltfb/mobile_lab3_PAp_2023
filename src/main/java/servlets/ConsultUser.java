package servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.DataTypeUser;
import logic.Factory;
import presentation.IUser;

@WebServlet("/ConsultUser")
public class ConsultUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IUser iUser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultUser() {
		Factory factory = Factory.getInstance();
		iUser = factory.getIUser();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("estoy en el get");
		List<DataTypeUser> users = iUser.listUsers();
		// Colocar la lista de usuarios en el alcance de solicitud
		request.setAttribute("users", users);
		// Redireccionar a la página JSP para mostrar la lista de usuarios
		request.getRequestDispatcher("consultUser.jsp").forward(request, response);
	}
}