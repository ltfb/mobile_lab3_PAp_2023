package utils;

import java.util.Base64;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import logic.DataTypeProvider;
import logic.DataTypeUser;

public class Utils {
	
	public static void setSession(HttpServletRequest request, DataTypeUser dtu) {
		HttpSession session = request.getSession();

		session.setAttribute("connected", true);
		session.setAttribute("uNickname", dtu.getNickname());
		session.setAttribute("uEmail", dtu.getEmail());
		session.setAttribute("uLastName", dtu.getLastName());
		session.setAttribute("uName", dtu.getName());
		if (dtu.getImage() != null) {
			System.out.println(dtu.getImage());
			String base64Image = Base64.getEncoder().encodeToString(dtu.getImage());
			session.setAttribute("uImage", base64Image);
		}

		if (dtu instanceof DataTypeProvider) {
			System.out.println("Soy un provider");
			System.out.println(((DataTypeProvider) dtu).getDescription());
			System.out.println(((DataTypeProvider) dtu).getWebSite());
			session.setAttribute("uUserType", "provider");
		} else {
			System.out.println("Soy un turista");
			session.setAttribute("uUserType", "tourist");
		}
	}

	public static String encodePassword(String password) {
		String encodedString = Base64.getEncoder().encodeToString(password.getBytes());
		return encodedString;
	}

	public static String decodePassword(String passwordEncoded) {
		byte[] decodedBytes = Base64.getDecoder().decode(passwordEncoded);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}

}
