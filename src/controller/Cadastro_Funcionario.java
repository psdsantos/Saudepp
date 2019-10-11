package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class Cadastro_Funcionario implements Initializable {
	@FXML
	private BorderPane cadastroFuncionarioPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void loadCRUD_FuncionarioView(ActionEvent event) {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/CRUD_Funcionario.fxml").toUri().toURL());
			
			cadastroFuncionarioPane.getChildren().clear();
			cadastroFuncionarioPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
