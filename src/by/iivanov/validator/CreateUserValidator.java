package by.iivanov.validator;

import by.iivanov.dto.CreateUserDto;
import by.iivanov.entity.Gender;
import by.iivanov.util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {

	private static final CreateUserValidator INSTANCE = new CreateUserValidator();

	@Override
	public ValidationResult isValid(CreateUserDto object) {
		ValidationResult validationResult = new ValidationResult();

		if (!LocalDateFormatter.isValid(object.getBirthday())) {
			validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
		}

		if (Gender.find(object.getGender()).isEmpty()) {
			validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
		}

		return validationResult;
	}

	public static CreateUserValidator getInstance() {
		return INSTANCE;
	}
}
