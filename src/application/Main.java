package application;

import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.db.DB;
import model.exceptions.InvalidDBPropertiesException;
import model.util.WindowsParam;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			root.setCenter(FXMLLoader.<BorderPane>load(Paths.get("src/view/Login.fxml").toUri().toURL()));
			
			
			Scene scene = new Scene(root, WindowsParam.getWidth(), WindowsParam.getHeight());
			scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Saude++");
			primaryStage.setResizable(true);
			primaryStage.getIcons().add(new Image("model/resources/saudeIcon.png"));
			primaryStage.show();
			
			DB.closeConnection();
			if(DB.getConnection() == null) throw new InvalidDBPropertiesException();
			DB.changeDateStyle("ISO, DMY");
		} catch (InvalidDBPropertiesException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("AVISO");
			alert.setHeaderText("Propriedades do banco de dados inválidas");
			alert.setContentText("Verifique o arquivo de propriedades.");
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
