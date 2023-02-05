package by.iivanov.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {

	private Long id;
	private String fightNo;
	private LocalDateTime departureDate;
	private String departureAirportCode;
	private LocalDateTime arrivalDate;
	private String arrivalAirportCode;
	private Integer aircraftId;
	private FlightStatus status;

	public Flight(Long id, String fightNo, LocalDateTime departureDate, String departureAirportCode,
				  LocalDateTime arrivalDate, String arrivalAirportCode, Integer aircraftId, FlightStatus status) {
		this.id = id;
		this.fightNo = fightNo;
		this.departureDate = departureDate;
		this.departureAirportCode = departureAirportCode;
		this.arrivalDate = arrivalDate;
		this.arrivalAirportCode = arrivalAirportCode;
		this.aircraftId = aircraftId;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFightNo() {
		return fightNo;
	}

	public void setFightNo(String fightNo) {
		this.fightNo = fightNo;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalAirportCode() {
		return arrivalAirportCode;
	}

	public void setArrivalAirportCode(String arrivalAirportCode) {
		this.arrivalAirportCode = arrivalAirportCode;
	}

	public Integer getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Integer aircraftId) {
		this.aircraftId = aircraftId;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Flight flight = (Flight) o;
		return Objects.equals(id, flight.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Flight{" +
				"id=" + id +
				", fightNo='" + fightNo + '\'' +
				", departureDate=" + departureDate +
				", departureAirportCode='" + departureAirportCode + '\'' +
				", arrivalDate=" + arrivalDate +
				", arrivalAirportCode='" + arrivalAirportCode + '\'' +
				", aircraftId=" + aircraftId +
				", status=" + status +
				'}';
	}
}
