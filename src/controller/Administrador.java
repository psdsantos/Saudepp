package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Administrador implements Initializable {
	@FXML
	private AnchorPane administradorPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void loadCRUD_FuncionarioView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			pane = FXMLLoader.<AnchorPane>load(Paths.get("src/view/CRUD_Funcionario.fxml").toUri().toURL());
	   
	        administradorPane.getChildren().setAll(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
