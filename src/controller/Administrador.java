package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Administrador implements Initializable {
	@FXML
	private AnchorPane administradorPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void loadCRUD_FuncionarioView(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("/view/CRUD_Funcionario.fxml"));
	   
	        Scene scene = new Scene(fxmlLoader.load(), 1250, 950);
	        Stage stage = new Stage();
	        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	        stage.setTitle("New Window");
	        stage.setScene(scene);
	        stage.setTitle("Saúde++");
			stage.setResizable(false);
			stage.getIcons().add(new Image("model/resources/saudeIcon.png"));
	        stage.show();
			administradorPane.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
