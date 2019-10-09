package model.application;

import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = new AnchorPane();
			
			URL fxmlUrl;
			fxmlUrl = Paths.get("src/view/Administrador.fxml").toUri().toURL();
			root = FXMLLoader.<AnchorPane>load(fxmlUrl);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Saúde++");
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("model/resources/saudeIcon.png"));
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
