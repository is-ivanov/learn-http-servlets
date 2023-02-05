package by.iivanov.dto;

public record FlightDto(Long id, String description) {

	@Override
	public String toString() {
		return "FlightDto{" +
				"id=" + id +
				", description='" + description + '\'' +
				'}';
	}
}
