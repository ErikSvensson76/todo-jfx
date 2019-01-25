package se.lexicon.erik.todo_app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.lexicon.erik.todo_app.data_access.TodoItemFileDao;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		String fxmlFile = "/fxml/mainwindow.fxml";
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
		primaryStage.setTitle("Title");
		primaryStage.setScene(new Scene(root, 900, 500));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		try {
			TodoItemFileDao.get().storeTodoItems();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void init() throws Exception {
		try {
			TodoItemFileDao.get().loadTodoItems();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	

}
