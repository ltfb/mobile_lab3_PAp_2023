package servlets;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import exceptions.UserException;
import logic.DataTypeProvider;
import logic.DataTypeTourist;
import logic.Factory;
import presentation.IUser;
import utils.Utils;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
@MultipartConfig
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IUser iUser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUser() {
		Factory factory = Factory.getInstance();
		iUser = factory.getIUser();
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
		String userName = request.getParameter("inputUserName");
		String userLastName = request.getParameter("inputUserLastName");
		String nickname = request.getParameter("inputUserNickname");
		String email = request.getParameter("inputUserEmail");
		String birthday = request.getParameter("inputUserBirthday");
		String password = request.getParameter("inputUserPassword");
		Part imagePart = request.getPart("fileUserImg");
		byte[] imageInBytes = null;
		if (imagePart != null) {
			// prints out some information for debugging
			System.out.println(imagePart.getName());
			System.out.println(imagePart.getSize());
			System.out.println(imagePart.getContentType());

			// obtains input stream of the upload file
			// the InputStream will point to a stream that contains
			// the contents of the file
			InputStream inputStream = imagePart.getInputStream();

			imageInBytes = new byte[(int) imagePart.getSize()];
			inputStream.read(imageInBytes);
			inputStream.close();
			System.out.println("img:"+imageInBytes.toString());
		}
		String description = request.getParameter("description");
		String webSite = request.getParameter("website");
		String nationality = request.getParameter("nationality");

		try {
			System.out.println("Creando User...");
			System.out.println("Contrasenia codificada: " + Utils.encodePassword(password));
			System.out.println("Contrasenia decodificada: " + password);
			if (nationality != null) { // es turista
				iUser.addTourist(new DataTypeTourist(nickname, userName, userLastName, email, birthday,
						Utils.encodePassword(password), nationality,imageInBytes));
				request.setAttribute("message", "Se ha ingresado con exito al turista " + nickname);
			} else { // es proveedor
				iUser.addProvider(new DataTypeProvider(nickname, userName, userLastName, email, birthday,
						Utils.encodePassword(password), description, webSite,imageInBytes));
				request.setAttribute("message", "Se ha ingresado con exito al proveedor " + nickname);
			}
			rd = request.getRequestDispatcher("login.jsp");
			// TODO: agregar mensaje de exito cuando se crea usuario
		} catch (UserException ue) {
			request.setAttribute("error", ue.getMessage());
			rd = request.getRequestDispatcher("createUser.jsp");
		}
		rd.forward(request, response);
	}

}
