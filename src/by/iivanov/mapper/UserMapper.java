package by.iivanov.mapper;

import by.iivanov.dto.UserDto;
import by.iivanov.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto>{

	private static final UserMapper INSTANCE = new UserMapper();

	@Override
	public UserDto mapFrom(User object) {
		return UserDto.builder()
				.id(object.getId())
				.name(object.getName())
				.birthday(object.getBirthday())
				.email(object.getEmail())
				.image(object.getImage())
				.role(object.getRole())
				.gender(object.getGender())
				.build();
	}

	public static UserMapper getInstance() {
		return INSTANCE;
	}

}
