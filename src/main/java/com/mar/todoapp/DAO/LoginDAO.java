package com.mar.todoapp.DAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mar.todoapp.model.LoginBean;
import com.mar.todoapp.util.JDBCUtils;

public class LoginDAO 
{
	public boolean validate(LoginBean loginBean) throws ClassNotFoundException
	{
		boolean status = false;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try (Connection connection = JDBCUtils.getConnection())
		{
			PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ? ");
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());
			
			System.out.println(preparedStatement);
		
			ResultSet resultSet = preparedStatement.executeQuery();
			status = resultSet.next();
			
		}
		catch (SQLException e)
		{
			JDBCUtils.printSQLException(e);
		}
		
		return status;
	}
}