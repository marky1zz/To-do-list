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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return 0;
	}
}
