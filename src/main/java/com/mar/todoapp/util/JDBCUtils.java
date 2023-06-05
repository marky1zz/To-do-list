package com.mar.todoapp.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils 
{
	private static String url = "jdbc:mysql://localhost:3306/demo";
	private static String name = "root";
	private static String password = "";
	
	public static Connection getConnection()
	{
		Connection connection = null;
		
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, name, password);
        } 
		catch (SQLException e) 
		{
            e.printStackTrace();
        } 
		catch (ClassNotFoundException e) 
		{
            e.printStackTrace();
        }
		
		return connection;
	}
	
	public static void printSQLException(SQLException ex)
	{
		for (Throwable e: ex)
		{
			if (e instanceof SQLException)
			{
				e.printStackTrace(System.err);
				System.out.println("SQLState: " + ((SQLException) e).getSQLState());
				System.out.println("Error code : " + ((SQLException) e).getErrorCode());
				System.out.println("Message: " + ((SQLException) e).getMessage());
				Throwable t = ex.getCause();
				while (t != null)
				{
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	public static Date getSQLDate(LocalDate date)
	{
		return java.sql.Date.valueOf(date);
	}
	
	public static LocalDate getUtilDate(Date sqlDate)
	{
		return sqlDate.toLocalDate();
	}
}