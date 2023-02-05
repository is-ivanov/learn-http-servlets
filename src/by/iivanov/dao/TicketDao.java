package by.iivanov.dao;

import by.iivanov.entity.Ticket;
import by.iivanov.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {

	private static final TicketDao INSTANCE = new TicketDao();

	private static final String FIND_ALL_BY_FLIGHT_ID = """
			SELECT *
			FROM ticket
			WHERE flight_id = ?
			""";

	private TicketDao() {
	}

	public static TicketDao getInstance() {
		return INSTANCE;
	}

	public List<Ticket> findAllByFlightId(Long flightId) {
		try (Connection connection = ConnectionManager.get();
			 PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID)) {
			preparedStatement.setObject(1, flightId);

			ResultSet resultSet = preparedStatement.executeQuery();
			List<Ticket> tickets = new ArrayList<>();
			while (resultSet.next()) {
				tickets.add(buildTicket(resultSet));
			}
			return tickets;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Ticket buildTicket(ResultSet resultSet) throws SQLException {
		return new Ticket(
				resultSet.getLong("id"),
				resultSet.getString("passenger_no"),
				resultSet.getString("passenger_name"),
				resultSet.getLong("flight_id"),
				resultSet.getString("seat_no"),
				resultSet.getBigDecimal("cost")
		);
	}

	@Override
	public List<Ticket> findAll() {
		return null;
	}

	@Override
	public Optional<Ticket> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}

	@Override
	public void update(Ticket entity) {

	}

	@Override
	public Ticket save(Ticket entity) {
		return null;
	}
}
