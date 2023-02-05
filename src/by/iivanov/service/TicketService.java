package by.iivanov.service;

import by.iivanov.dao.TicketDao;
import by.iivanov.dto.TicketDto;
import by.iivanov.entity.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {

	private static final TicketService INSTANCE = new TicketService();

	private final TicketDao ticketDao = TicketDao.getInstance();

	private TicketService() {
	}

	public static TicketService getInstance() {
		return INSTANCE;
	}

	public List<TicketDto> findAllByFlightId(Long flightId) {
		return ticketDao.findAllByFlightId(flightId).stream()
				.map(ticket -> new TicketDto(ticket.getId(), ticket.getFlightId(), ticket.getSeatNo()))
				.collect(Collectors.toList());
	}
}
