package by.iivanov.service;

import by.iivanov.dao.FlightDao;
import by.iivanov.dto.FlightDto;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

	private static final FlightService INSTANCE = new FlightService();

	private final FlightDao flightDao = FlightDao.getInstance();

	private FlightService() {
	}

	public List<FlightDto> findAll() {
		return flightDao.findAll().stream().map(flight -> new FlightDto(
				flight.getId(),
						"""
						%s - %s - %s
						""".formatted(flight.getDepartureAirportCode(), flight.getArrivalAirportCode(), flight.getStatus())))
				.collect(Collectors.toList());
	}

	public static FlightService getInstance() {
		return INSTANCE;
	}
}
