package by.iivanov.servlet;

import by.iivanov.service.FlightService;
import by.iivanov.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

	private final FlightService flightService = FlightService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("flights", flightService.findAll());
		req.getRequestDispatcher(JspHelper.getPath("flights"))
				.forward(req, resp);
	}
}
