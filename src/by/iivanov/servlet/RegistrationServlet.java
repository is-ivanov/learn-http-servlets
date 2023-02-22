package by.iivanov.servlet;

import by.iivanov.dto.CreateUserDto;
import by.iivanov.exception.ValidationException;
import by.iivanov.service.UserService;
import by.iivanov.util.JspHelper;
import by.iivanov.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(UrlPath.REGISTRATION)
public class RegistrationServlet extends HttpServlet {

	private final UserService userService = UserService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("roles", List.of("USER", "ADMIN"));
		req.setAttribute("genders", List.of("MALE", "FEMALE"));
		req.getRequestDispatcher(JspHelper.getPath("registration"))
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CreateUserDto userDto = CreateUserDto.builder()
				.name(req.getParameter("name"))
				.birthday(req.getParameter("birthday"))
				.image(req.getPart("image"))
				.email(req.getParameter("email"))
				.password(req.getParameter("password"))
				.role(req.getParameter("role"))
				.gender(req.getParameter("gender"))
				.build();

		try {
			userService.create(userDto);
			resp.sendRedirect("/login");
		} catch (ValidationException e) {
			req.setAttribute("errors", e.getErrors());
			doGet(req, resp);
		}
	}
}
