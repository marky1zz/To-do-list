package com.mar.todoapp.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.mar.todoapp.DAO.TodoDAO;
import com.mar.todoapp.DAO.TodoDAOImpl;
import com.mar.todoapp.model.Todo;

@WebServlet("/")
public class TodoController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private TodoDAO todoDAO;
	
	public void init()
	{
		todoDAO = new TodoDAOImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getServletPath();
		
		try
		{
			switch (action)
			{
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertTodo(request, response);
					break;
				case "/delete":
					deleteTodo(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateTodo(request, response);
					break;
				case "/list":
					listTodos(request, response);
					break;
				default:
					RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
					dispatcher.forward(request, response);
					break;
			}
		}
		catch (SQLException ex)
		{
			throw new ServletException(ex);
		}
	}
	
	private void listTodos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		List <Todo> listTodo = todoDAO.selectAllTodos();
		
		request.setAttribute("listTodo", listTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		Todo existingTodo = todoDAO.selectTodo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);
	}
	
	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		Boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		//System.out.println(isDone);
		
		/*DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"),df);*/
		
		Todo newTodo = new Todo(title, username, description, LocalDate.now(), isDone);
		todoDAO.insertTodo(newTodo);
		response.sendRedirect("list");
	}
	
	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		Boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		//System.out.println(isDone);
		
		Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);
		
		todoDAO.updateTodo(updateTodo);
		
		response.sendRedirect("list");
	}
	
	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		todoDAO.deleteTodo(id);
		
		response.sendRedirect("list");
	}
}