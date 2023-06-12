package com.mar.todoapp.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;

import com.mar.todoapp.DAO.UserDAO;
import com.mar.todoapp.model.User;

@WebServlet("/register")
public class UserController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
	
	public void init()
	{
		userDao = new UserDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		register(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("register.register.jsp");
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		User employee = new User();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUsername(userName);
		employee.setPassword(password);
		
		try
		{
			int result = userDao.registerEmployee(employee);
			
			if (result == 1)
			{
				request.setAttribute("NOTIFICATION", "User Registered successfully!");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
		dispatcher.forward(request, response);
	}
}
