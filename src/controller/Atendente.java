package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class Atendente implements Initializable {
	@FXML
	private BorderPane administradorPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	
	@FXML
	private void loadCRUD_AtendenteView(ActionEvent event) {
		try {
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/CRUD_Atendente.fxml").toUri().toURL());
			
			administradorPane.getChildren().clear();
			administradorPane.setCenter(pane);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void loadLoginView(ActionEvent event) {
		try {
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Login.fxml").toUri().toURL());
			
			administradorPane.getChildren().clear();
			administradorPane.setCenter(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void loadAgendaView(ActionEvent event) {
		try {
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Agenda.fxml").toUri().toURL());
			
			administradorPane.getChildren().clear();
			administradorPane.setCenter(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
