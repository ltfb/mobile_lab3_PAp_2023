package servlets;

import java.io.IOException;
import java.util.List;

/*import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;*/

import exceptions.UserException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.DataTypeTourist;
import logic.DataTypeUser;
import logic.Factory;
import presentation.IUser;
import utils.Utils;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Factory factory;
	private IUser iUser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		factory = Factory.getInstance();
		iUser = factory.getIUser();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;

		String user = request.getParameter("user");
		String password = request.getParameter("password");
		if (!user.isEmpty() && !password.isEmpty()) {
			DataTypeUser dataUser = null;
			try {
				dataUser = this.userValidation(user, password);

				Utils.setSession(request, dataUser);

				request.setAttribute("message", "Bienvenido " + dataUser.getName() + " " + dataUser.getLastName());
				rd = request.getRequestDispatcher("index.jsp");
			} catch (UserException ue) {
				request.setAttribute("error", ue.getMessage());
				rd = request.getRequestDispatcher("login.jsp");
			}
		} else {
			request.setAttribute("error", "Debe ingresar usuario y contraseña");
			rd = request.getRequestDispatcher("login.jsp");
		}
		rd.forward(request, response);
	}

	// el unico error que puede haber aca es si hay un email igual a un nickname de
	// otro usuario. Entonces para que esto se evite habria que controlar tambien
	// esa validacion que un email no sea igual a un nickname de otro user en la
	// creacion usuario
	private DataTypeUser userValidation(String username, String password) throws UserException {
		DataTypeUser dtuReturn = null;
		List<DataTypeUser> dtuList = iUser.listUsers();
		for (DataTypeUser dtu : dtuList) {
			if (dtu.getNickname().equals(username)) {
				dtuReturn = dtu;
				System.out.println("Contrasenia de user codificada: " + dtu.getPassword());
				System.out.println("Contrasenia de user decodificada: " + Utils.decodePassword(dtu.getPassword()));
				if (!dtu.getPassword().equals(Utils.encodePassword(password))) {
					throw new UserException("Contraseña incorrecta");
				}
				break;
			} else if (dtu.getEmail().equals(username)) {
				dtuReturn = dtu;
				System.out.println("Contrasenia de user codificada: " + dtu.getPassword());
				System.out.println("Contrasenia de user decodificada: " + Utils.decodePassword(dtu.getPassword()));
				if (!dtu.getPassword().equals(Utils.encodePassword(password))) {
					throw new UserException("Contraseña incorrecta");
				}
				break;
			}
		}
		if (dtuReturn == null) {
			throw new UserException("El usuario no existe");
		} else {
				if (dtuReturn instanceof DataTypeTourist)
					System.out.println("Inicio sesion como turista");
				else throw new UserException("El usuario no es un turista");
		}
		return dtuReturn;
	}

}