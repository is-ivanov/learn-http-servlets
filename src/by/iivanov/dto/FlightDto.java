package by.iivanov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class FlightDto {
	Long id;
	String description;

}
