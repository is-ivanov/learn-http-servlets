package by.iivanov.sevlet;

import by.iivanov.dto.FlightDto;
import by.iivanov.service.FlightService;
import by.iivanov.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

	private final FlightService flightService = FlightService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<FlightDto> flights = flightService.findAll();
		req.setAttribute("flights", flights);
		req.getSession().setAttribute("flightsMap", flights.stream()
				.collect(Collectors.toMap(FlightDto::getId, FlightDto::getDescription)));

		req.getRequestDispatcher(JspHelper.getPath("content"))
				.forward(req, resp);
	}
}
