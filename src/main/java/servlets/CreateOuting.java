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
import jakarta.servlet.http.Part;

import enums.ActivityState;
import exceptions.ActivityException;
import exceptions.OutingException;
import logic.DataTypeActivity;
import logic.DataTypeOuting;
import logic.Factory;
import presentation.IActivity;
import presentation.IOuting;

/**
 * Servlet implementation class CreateOuting
 */
@WebServlet("/CreateOuting")
@MultipartConfig
public class CreateOuting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IActivity iActivity;
	IOuting iOuting;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateOuting() {
		Factory factory = Factory.getInstance();
		iOuting = factory.getIOuting();
		iActivity = factory.getIActivity();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataTypeActivity[] dtas = iActivity.listTouristActivities(ActivityState.CONFIRMADA);
		request.setAttribute("activities", dtas);
		request.getRequestDispatcher("createOuting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		String actName = request.getParameter("combobox-act-type");
		String outName = request.getParameter("inputOutName");
		String outDateAndHour = request.getParameter("inputOutDate");
		String outPlace = request.getParameter("inputOutPlace");
		int maxTourists = Integer.parseInt(request.getParameter("inputOutMaxTourist"));
		Part imagePart = request.getPart("fileOutImg");
		byte[] imageInBytes = null;
		if (imagePart != null) {
			System.out.println(imagePart.getName());
			System.out.println(imagePart.getSize());
			System.out.println(imagePart.getContentType());

			InputStream inputStream = imagePart.getInputStream();

			imageInBytes = new byte[(int) imagePart.getSize()];
			inputStream.read(imageInBytes);
			inputStream.close();
		}

		try {
			System.out.println("Creando Salida...");

			LocalDateTime entryDate = LocalDateTime.now();
			String outDate = outDateAndHour.split("T")[0];
			String outHour = outDateAndHour.split("T")[1];
			DataTypeOuting dto = new DataTypeOuting(outName, outDate, outHour, maxTourists, outPlace, entryDate,
					imageInBytes);
			iOuting.outingRegistration(actName, dto);

			request.setAttribute("message", "Se ha ingresado con exito la salida: " + outName);
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} catch (OutingException oe) {
			request.setAttribute("error", oe.getMessage());
			doGet(request, response);
		} catch (ActivityException ae) {
			request.setAttribute("error", ae.getMessage());
			doGet(request, response);
		}
	}

}
