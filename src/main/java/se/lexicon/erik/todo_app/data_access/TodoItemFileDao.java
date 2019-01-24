package se.lexicon.erik.todo_app.data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import se.lexicon.erik.todo_app.model.TodoItem;

public class TodoItemFileDao {
	private static TodoItemFileDao instance = new TodoItemFileDao();
	private static String fileName = "TodoListItems.txt";
	
	private List<TodoItem> todoItems;
	
	private TodoItemFileDao() {
		todoItems = FXCollections.observableArrayList();
	}
	
	public static TodoItemFileDao get() {
		return instance;
	}
	
	public void loadTodoItems() throws IOException{
		Path path = Paths.get(fileName);
		BufferedReader br = Files.newBufferedReader(path);
		
		String input;
		
		try {
			while((input = br.readLine()) != null) {
				String[] itemPieces = input.split("\t");
				
				String shortDescription = itemPieces[0];
				String details = itemPieces[1];
				LocalDate deadLine = LocalDate.parse(itemPieces[2]);
				
				TodoItem item = new TodoItem(shortDescription,details, deadLine);
				todoItems.add(item);								
			}
		}finally {
			if(br != null) {
				br.close();
			}
		}
	}
	
	public void storeTodoItems() throws IOException{
		Path path = Paths.get(fileName);
		BufferedWriter bw = Files.newBufferedWriter(path);
		try {
			Iterator<TodoItem> iterator = todoItems.iterator();
			while(iterator.hasNext()) {
				TodoItem item = iterator.next();
				bw.write(String.format("%s\t%s\t%s",
						item.getShortDescription(),
						item.getDetails(),
						item.getDeadLine()
						));
				bw.newLine();
			}
			
		}finally {
			if(bw != null) {
				bw.close();
			}
		}		
	}
}
