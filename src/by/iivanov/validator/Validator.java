package by.iivanov.validator;

public interface Validator<T> {

	ValidationResult isValid(T object);

}
