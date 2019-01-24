package se.lexicon.erik.todo_app.controller;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import se.lexicon.erik.todo_app.data_access.TodoItemDaoList;
import se.lexicon.erik.todo_app.model.TodoItem;

public class Controller {
	
	private TodoItemDaoList todoItemDao = TodoItemDaoList.get();
	
	@FXML private ListView<TodoItem> todoListView;
	@FXML private TextArea itemDetailsTextArea;
	@FXML private Label deadlineLabel;
	
	public void initialize() {
		todoItemDao.save(new TodoItem("Fix Car", "Buy car light and install them", LocalDate.parse("2019-03-25")));
		todoItemDao.save(new TodoItem("JavaFX material", "Finalize JavaFX material for future courses", LocalDate.parse("2019-07-10")));
		todoItemDao.save(new TodoItem("Selma's birthday", "Buy skateboard and cake", LocalDate.parse("2019-03-25")));
		todoItemDao.save(new TodoItem("Clean my desk", "Remove old papers and keep things tidy", LocalDate.parse("2019-02-10")));
		todoItemDao.save(new TodoItem("Prepare Lecture", "Finish preparing lecture for group 23", LocalDate.parse("2019-03-10")));
		
		todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
			@Override
			public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
				if(newValue != null) {
					TodoItem item = todoListView.getSelectionModel().getSelectedItem();
					itemDetailsTextArea.setText(item.getDetails());
					deadlineLabel.setText(item.getDeadLine().toString());
				}
			}			
		});
		
		//Populate the todoListView
		todoListView.getItems().setAll(todoItemDao.getAllTodoItems());
		
		//Makes sure we can only select one item at a time
		todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		todoListView.getSelectionModel().selectFirst();
		
	}
	
	@FXML
	public void handleClickListView() {
		TodoItem item = todoListView.getSelectionModel().getSelectedItem();
		itemDetailsTextArea.setText(item.getDetails());
		deadlineLabel.setText(item.getDeadLine().toString());
//		StringBuilder sb = new StringBuilder(item.getDetails());
//		sb.append("\n\n\n\n");
//		sb.append("Due: ");
//		sb.append(item.getDeadLine());
//		itemDetailsTextArea.setText(sb.toString());
	}
	
}
