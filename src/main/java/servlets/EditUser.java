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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import logic.DataTypeProvider;
import logic.DataTypeTourist;
import logic.DataTypeUser;
import logic.Factory;
import presentation.IUser;
import utils.Utils;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
@MultipartConfig
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IUser iUser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUser() {
		Factory factory = Factory.getInstance();
		iUser = factory.getIUser();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		DataTypeUser dtu = iUser.dataUser((String) session.getAttribute("uNickname"));

		request.setAttribute("userName", dtu.getName());
		request.setAttribute("userLastName", dtu.getLastName());
		request.setAttribute("userNickname", dtu.getNickname());
		request.setAttribute("userEmail", dtu.getEmail());
		request.setAttribute("userBirthday", dtu.getBirthday());
		request.setAttribute("userPassword", dtu.getPassword());
//		if (dtu.getImage() != null) {
//			request.setAttribute("userImage", Base64.getEncoder().encodeToString(dtu.getImage()));
//		}
//		TODO: falta la imagen
		if (dtu instanceof DataTypeProvider) {
			DataTypeProvider dtp = (DataTypeProvider) dtu;
			request.setAttribute("userDescription", dtp.getDescription());
			request.setAttribute("userWebsite", dtp.getWebSite());
		} else if (dtu instanceof DataTypeTourist) {
			DataTypeTourist dtt = (DataTypeTourist) dtu;
			request.setAttribute("userNationality", dtt.getNationality());
		}

		request.getRequestDispatcher("editUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;

		HttpSession session = request.getSession();
		DataTypeUser dtu = iUser.dataUser((String) session.getAttribute("uNickname"));
		dtu = (dtu instanceof DataTypeProvider) ? (DataTypeProvider) dtu : (DataTypeTourist) dtu;

		String userName = request.getParameter("inputEditName");
		String userLastName = request.getParameter("inputEditLastName");
		String birthday = request.getParameter("inputEditBirthday");
		String password = request.getParameter("inputEditPassword");
		Part imagePart = request.getPart("fileEditImg");
		System.out.println(userLastName);
		System.out.println(userName);
		System.out.println(password);
		System.out.println(birthday);
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
		} else {
			imageInBytes = dtu.getImage();
		}
		String description = request.getParameter("inputEditDescription");
		String website = request.getParameter("inputEditWebsite");
		String nationality = request.getParameter("inputEditNationality");

		System.out.println("Editando User...");

		password = password.equals(dtu.getPassword()) ? dtu.getPassword() : Utils.encodePassword(password);
		if (nationality != null) { // es turista
			DataTypeTourist dtt = new DataTypeTourist(dtu.getNickname(), userName, userLastName, dtu.getEmail(),
					birthday, dtu.getPassword(), nationality, imageInBytes);
			iUser.editUser(dtt);
			Utils.setSession(request, dtt);
		} else { // es proveedor
			DataTypeProvider dtp = new DataTypeProvider(dtu.getNickname(), userName, userLastName, dtu.getEmail(),
					birthday, dtu.getPassword(), description, website, imageInBytes);
			iUser.editUser(dtp);
			Utils.setSession(request, dtp);
		}

		rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		// TODO: agregar mensaje de exito cuando se edita usuario
	}

}
