package se.lexicon.erik.todo_app.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import se.lexicon.erik.todo_app.data_access.TodoItemDao;
import se.lexicon.erik.todo_app.data_access.TodoItemFileDao;
import se.lexicon.erik.todo_app.model.TodoItem;

public class Controller {
	
	private TodoItemDao todoItemDao = TodoItemFileDao.get();
	
	@FXML private ListView<TodoItem> todoListView;
	@FXML private TextArea itemDetailsTextArea;
	@FXML private Label deadlineLabel;
	@FXML private BorderPane mainBorderPane;
	
	public void initialize() {
		
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
	public void showNewItemDialog() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/todoItemDialog.fxml"));
		try {
			dialog.getDialogPane().setContent(loader.load());
		}catch(IOException e) {
			System.out.println("Could not load the dialog");
			e.printStackTrace();
			return;
		}
		
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		Optional<ButtonType> result = dialog.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			DialogController controller = loader.getController();
			controller.processResults();
			todoListView.getItems().setAll(todoItemDao.getAllTodoItems());
		}else {
			System.out.println("Cancel pressed");
		}
	}
	
	@FXML
	public void handleClickListView() {
		TodoItem item = todoListView.getSelectionModel().getSelectedItem();
		itemDetailsTextArea.setText(item.getDetails());
		deadlineLabel.setText(item.getDeadLine().toString());
	}
	
}
