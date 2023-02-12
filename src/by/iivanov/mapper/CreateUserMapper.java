package by.iivanov.mapper;

import by.iivanov.dto.CreateUserDto;
import by.iivanov.entity.Gender;
import by.iivanov.entity.Role;
import by.iivanov.entity.User;
import by.iivanov.util.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

	private static final String IMAGE_FOLDER = "users\\";
	private static final CreateUserMapper INSTANCE = new CreateUserMapper();

	@Override
	public User mapFrom(CreateUserDto object) {
		return User.builder()
				.name(object.getName())
				.birthday(LocalDateFormatter.format(object.getBirthday()))
				.image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
				.email(object.getEmail())
				.password(object.getPassword())
				.gender(Gender.valueOf(object.getGender()))
				.role(Role.valueOf(object.getRole()))
				.build();
	}

	public static CreateUserMapper getInstance() {
		return INSTANCE;
	}
}
