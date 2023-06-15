package com.mar.todoapp.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.mar.todoapp.DAO.LoginDAO;
import com.mar.todoapp.model.LoginBean;

@WebServlet("/login")
public class LoginController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDAO;
	
	public void init()
	{
		loginDAO = new LoginDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("login/login.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		authenticate(request, response);
	}
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		try
		{
			if (loginDAO.validate(loginBean))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("list");
				dispatcher.forward(request, response);
			}
			else
			{
				HttpSession session = request.getSession();
				// session.setAttribute("user", username);
				//response.sendRedirect("login/login.jsp");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}	
}
