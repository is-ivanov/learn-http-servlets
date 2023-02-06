package by.iivanov.sevlet;

import by.iivanov.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {

	private static final String USER = "user";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserDto user = (UserDto) session.getAttribute(USER);
		if (user == null) {
			user = new UserDto(25L, "iuser@gmail.com");
			session.setAttribute(USER, user);
		}
	}
}
