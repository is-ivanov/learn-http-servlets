package by.iivanov.service;

import by.iivanov.dao.UserDao;
import by.iivanov.dto.CreateUserDto;
import by.iivanov.entity.User;
import by.iivanov.exception.ValidationException;
import by.iivanov.mapper.CreateUserMapper;
import by.iivanov.validator.CreateUserValidator;
import by.iivanov.validator.ValidationResult;

public class UserService {

	private static final UserService INSTANCE = new UserService();

	private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
	private final UserDao userDao = UserDao.getInstance();
	private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

	public Integer create(CreateUserDto userDto) {
//		validation
		ValidationResult validationResult = createUserValidator.isValid(userDto);
		if (!validationResult.isValid()) {
			throw new ValidationException(validationResult.getErrors());
		}
//		map
		User userEntity = createUserMapper.mapFrom(userDto);
		userDao.save(userEntity);
		return userEntity.getId();
	}

	public static UserService getInstance() {
		return INSTANCE;
	}
}
