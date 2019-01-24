package se.lexicon.erik.todo_app.data_access;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import se.lexicon.erik.todo_app.model.TodoItem;

public class TodoItemDaoList {
	
	private List<TodoItem> todoItems;
	private static final TodoItemDaoList instance;
	
	private TodoItemDaoList() {
		todoItems = new ArrayList<>();
		
	}
	
	static {
		instance = new TodoItemDaoList();
		
	}
	
	public static TodoItemDaoList get() {
		return instance;
	}
	
	public List<TodoItem> getAllTodoItems(){
		return todoItems.stream()
				.sorted((o1,o2) -> o1.getDeadLine().compareTo(o2.getDeadLine()))
				.collect(Collectors.toList());
				
	}
	
	/**
	 * 
	 * @param  item to persist
	 * @return Parameter sent in is returned after persisting
	 * @throws IllegalArgumentException when passing a <b>null</b> parameter
	 */
	public TodoItem save(TodoItem item) throws IllegalArgumentException{
		if(item == null) {
			throw new IllegalArgumentException("TodoItem item was " + item);
		}
		
		todoItems.add(item);
		return item;
	}
	
	
	
	
	
	
	
	

}
