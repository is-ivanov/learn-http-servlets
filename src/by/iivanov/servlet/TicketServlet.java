package by.iivanov.servlet;

import by.iivanov.service.TicketService;
import by.iivanov.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

	private final TicketService ticketService = TicketService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long flightId = Long.valueOf(req.getParameter("flightId"));
		req.setAttribute("tickets", ticketService.findAllByFlightId(flightId));
		req.getRequestDispatcher(JspHelper.getPath("tickets"))
				.forward(req, resp);
	}
}
