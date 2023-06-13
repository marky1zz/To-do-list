package com.mar.todoapp.DAO;

import java.sql.SQLException;
import java.util.List;

import com.mar.todoapp.model.Todo;

public interface TodoDAO 
{
	void insertTodo(Todo todo) throws SQLException;
	
	Todo selectTodo(long todoId);
	
	List<Todo> selectAllTodos();
	
	boolean deleteTodo(int id) throws SQLException;
	
	boolean updateTodo(Todo todo) throws SQLException;
}