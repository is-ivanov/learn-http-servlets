package by.iivanov.service;

import by.iivanov.dao.UserDao;
import by.iivanov.dto.CreateUserDto;
import by.iivanov.dto.UserDto;
import by.iivanov.entity.User;
import by.iivanov.exception.ValidationException;
import by.iivanov.mapper.CreateUserMapper;
import by.iivanov.mapper.UserMapper;
import by.iivanov.validator.CreateUserValidator;
import by.iivanov.validator.ValidationResult;
import lombok.SneakyThrows;

import java.util.Optional;

public class UserService {

	private static final UserService INSTANCE = new UserService();

	private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
	private final UserDao userDao = UserDao.getInstance();
	private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
	private final ImageService imageService = ImageService.getInstance();
	private final UserMapper userMapper = UserMapper.getInstance();

	@SneakyThrows
	public Integer create(CreateUserDto userDto) {
//		validation
		ValidationResult validationResult = createUserValidator.isValid(userDto);
		if (!validationResult.isValid()) {
			throw new ValidationException(validationResult.getErrors());
		}
//		map
		User userEntity = createUserMapper.mapFrom(userDto);
		imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
		userDao.save(userEntity);
		return userEntity.getId();
	}

	public Optional<UserDto> login(String email, String password) {
		return userDao.findByEmailAndPassword(email, password)
				.map(userMapper::mapFrom);
	}

	public static UserService getInstance() {
		return INSTANCE;
	}
}
