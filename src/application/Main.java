package application;

import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.WindowsParam;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			URL fxmlUrl;
			fxmlUrl = Paths.get("src/view/Login.fxml").toUri().toURL();
			root = FXMLLoader.<BorderPane>load(fxmlUrl);
			
			
			
			
			Scene scene = new Scene(root, WindowsParam.getWidth(), WindowsParam.getHeight());
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Saude++");
			primaryStage.setResizable(true);
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
