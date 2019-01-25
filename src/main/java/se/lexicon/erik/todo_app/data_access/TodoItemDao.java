package se.lexicon.erik.todo_app.data_access;

import java.util.List;

import se.lexicon.erik.todo_app.model.TodoItem;

public interface TodoItemDao {

	List<TodoItem> getAllTodoItems();

	/**
	 * 
	 * @param  item to persist
	 * @return Parameter sent in is returned after persisting
	 * @throws IllegalArgumentException when passing a <b>null</b> parameter
	 */
	TodoItem save(TodoItem item) throws IllegalArgumentException;
	
	void remove(TodoItem item) throws IllegalArgumentException;

}