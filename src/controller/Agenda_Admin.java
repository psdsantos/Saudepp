package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import model.util.DateHandling;

public class Agenda_Admin implements Initializable {

	@FXML
	private BorderPane agendaPane;
	@FXML
	private DatePicker datePicker;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	               
		DateHandling.toMilitaryFormat(datePicker);
	}
	
	@FXML
	private void loadAdministradorView(ActionEvent event) {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Administrador.fxml").toUri().toURL());
			
			agendaPane.getChildren().clear();
			agendaPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
