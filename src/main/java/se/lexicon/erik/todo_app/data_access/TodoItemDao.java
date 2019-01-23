package se.lexicon.erik.todo_app.data_access;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import se.lexicon.erik.todo_app.model.TodoItem;

public class TodoItemDao {
	
	private List<TodoItem> todoItems;
	private static final TodoItemDao instance;
	
	private TodoItemDao() {
		todoItems = new ArrayList<>();
		createSampleData();
	}
	
	static {
		instance = new TodoItemDao();
		
	}
	
	private void createSampleData() {
		todoItems.add(new TodoItem("Fix Car", "Buy car light and install them", LocalDate.parse("2019-03-25")));
		todoItems.add(new TodoItem("JavaFX material", "Finalize JavaFX material for future courses", LocalDate.parse("2019-07-10")));
		todoItems.add(new TodoItem("Selma's birthday", "Buy skateboard and cake", LocalDate.parse("2019-03-25")));
		todoItems.add(new TodoItem("Clean my desk", "Remove old papers and keep things tidy", LocalDate.parse("2019-02-10")));
		todoItems.add(new TodoItem("Prepare Lecture", "Finish preparing lecture for group 23", LocalDate.parse("2019-03-10")));
	}
	
	public static TodoItemDao get() {
		return instance;
	}
	
	public List<TodoItem> getAllTodoItems(){
		return this.todoItems;
	}
	
	
	
	
	
	

}
