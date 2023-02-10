package by.iivanov.dto;

import java.util.Objects;

public final class TicketDto {
	private final Long id;
	private final Long flightId;
	private final String seatNo;

	public TicketDto(Long id, Long flightId, String seatNo) {
		this.id = id;
		this.flightId = flightId;
		this.seatNo = seatNo;
	}

	public Long getId() {
		return id;
	}

	public Long getFlightId() {
		return flightId;
	}

	public String getSeatNo() {
		return seatNo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (TicketDto) obj;
		return Objects.equals(this.id, that.id) &&
				Objects.equals(this.flightId, that.flightId) &&
				Objects.equals(this.seatNo, that.seatNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, flightId, seatNo);
	}

	@Override
	public String toString() {
		return "TicketDto[" +
				"id=" + id + ", " +
				"flightId=" + flightId + ", " +
				"seatNo=" + seatNo + ']';
	}

}
