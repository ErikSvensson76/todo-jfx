package se.lexicon.erik.todo_app.controller;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import se.lexicon.erik.todo_app.data_access.TodoItemDao;
import se.lexicon.erik.todo_app.data_access.TodoItemFileDao;
import se.lexicon.erik.todo_app.model.TodoItem;

public class DialogController {
	
	private TodoItemDao todoItemDao = TodoItemFileDao.get();
	
	@FXML private TextField shortDescriptionField;
	@FXML private TextArea detailsArea;
	@FXML private DatePicker deadlinePicker;
	
	public void processResults() {
		String shortDescription = shortDescriptionField.getText().trim();
		String details = detailsArea.getText().trim();
		LocalDate deadline = deadlinePicker.getValue();
		try {
			todoItemDao.save(new TodoItem(shortDescription, details, deadline));
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}		
	}
	
}
