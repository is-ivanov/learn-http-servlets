package by.iivanov.dao;

import by.iivanov.entity.Gender;
import by.iivanov.entity.Role;
import by.iivanov.entity.User;
import by.iivanov.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

	private static final UserDao INSTANCE = new UserDao();

	private static final String SAVE_SQL =
			"INSERT INTO users(name, birthday, image, email, password, role, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM users WHERE email = ? AND password = ?";

	@Override
	public List<User> findAll() {
		return null;
	}

	@SneakyThrows
	public Optional<User> findByEmailAndPassword(String email, String password) {
		try (Connection connection = ConnectionManager.get();
			 PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			User user = null;
			if (resultSet.next()) {
				user = buildEntity(resultSet);
			}
			return Optional.ofNullable(user);
		}
	}

	@Override
	public Optional<User> findById(Integer id) {
		return Optional.empty();
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	@Override
	public void update(User entity) {
	}

	@Override
	@SneakyThrows
	public User save(User entity) {
		try (Connection connection = ConnectionManager.get();
			 PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setObject(1, entity.getName());
			preparedStatement.setObject(2, entity.getBirthday());
			preparedStatement.setObject(3, entity.getImage());
			preparedStatement.setObject(4, entity.getEmail());
			preparedStatement.setObject(5, entity.getPassword());
			preparedStatement.setObject(6, entity.getRole().name());
			preparedStatement.setObject(7, entity.getGender().name());

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			generatedKeys.next();

			entity.setId(generatedKeys.getObject("id", Integer.class));
			return entity;
		}
	}

	public static UserDao getInstance() {
		return INSTANCE;
	}

	private User buildEntity(ResultSet resultSet) throws SQLException {
		return User.builder()
				.id(resultSet.getObject("id", Integer.class))
				.name(resultSet.getObject("name", String.class))
				.birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
				.email(resultSet.getObject("email", String.class))
				.image(resultSet.getObject("image", String.class))
				.password(resultSet.getObject("password", String.class))
				.role(Role.valueOf(resultSet.getObject("role", String.class)))
				.gender(Gender.find(resultSet.getObject("gender", String.class)).orElse(null))
				.build();
	}
}
