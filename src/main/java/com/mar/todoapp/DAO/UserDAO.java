package com.mar.todoapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mar.todoapp.model.User;
import com.mar.todoapp.util.JDBCUtils;

public class UserDAO 
{
	public int registerEmployee(User employee)
	{
		String INSER_USER_SQL = "INSERT INTO users" + " (first_name, last_name, username, password) VALUES " + " (?, ?, ?, ?);";
		
		int result = 0;
		
		try (Connection connection = JDBCUtils.getConnection())
		{
			// Creating a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(INSER_USER_SQL);
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUsername());
			preparedStatement.setString(4, employee.getPassword());
			
			System.out.println(preparedStatement);
			
			result = preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			JDBCUtils.printSQLException(e);
		}		
		return result;
	}
}
