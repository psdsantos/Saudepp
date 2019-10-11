package application;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			URL fxmlUrl;
			fxmlUrl = Paths.get("src/view/Administrador.fxml").toUri().toURL();
			root = FXMLLoader.<BorderPane>load(fxmlUrl);
			
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();
			
			Scene scene = new Scene(root, 0.9*width, 0.9*height);
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
