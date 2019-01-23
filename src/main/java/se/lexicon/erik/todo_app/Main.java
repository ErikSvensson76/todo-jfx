package se.lexicon.erik.todo_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		String fxmlFile = "/fxml/mainwindow.fxml";
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
		primaryStage.setTitle("Title");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
