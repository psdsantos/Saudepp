package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class Login implements Initializable {
	@FXML
	private BorderPane LoginPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	
	@FXML
	private void loadAdministradorView(ActionEvent event) {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Administrador.fxml").toUri().toURL());
			
			LoginPane.getChildren().clear();
			LoginPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void loadAtendenteView(ActionEvent event) {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Atendente.fxml").toUri().toURL());
			
			LoginPane.getChildren().clear();
			LoginPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
