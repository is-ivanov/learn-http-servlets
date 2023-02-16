package by.iivanov.dto;

import by.iivanov.entity.Gender;
import by.iivanov.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
	Integer id;
	String name;
	LocalDate birthday;
	String email;
	String image;
	Role role;
	Gender gender;

}
