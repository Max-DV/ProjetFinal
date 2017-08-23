package io.robusta.auth.business;
import io.robusta.auth.dao.MySQLDatabaseConnection;
import io.robusta.auth.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserBusiness {
	private Connection connection;
	public UserBusiness(MySQLDatabaseConnection connection){
		this.connection = connection.getConnection();
	}
	public User findByEmail(String email) {
		try {
			String sql = "SELECT * FROM `users` WHERE email = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return userFactory(resultSet);
			else
				return null;
		} catch (SQLException e){
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)",e);
		}
	}
	private User userFactory(ResultSet resultSet) throws SQLException{
		User user = new User();
		String email = resultSet.getString("email");
		String name = resultSet.getString("name");
		String password = resultSet.getString("password");
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		return user;
	}
	public User findByName(String name) {
		try {
			String sql = "SELECT * FROM `users` WHERE name = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,name);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return userFactory(resultSet);
			else
				return null;
		} catch (SQLException e){
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)",e);
		}
	}