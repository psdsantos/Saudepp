package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;

public class CRUD_Funcionario implements Initializable {

	@FXML
	private BorderPane funcionarioPane;
	@FXML
	private RadioButton radioButtonAtendente;
	@FXML
	private RadioButton radioButtonMedico;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	               
	                
	}
	
	
	@FXML
	private void loadCadastro_FuncionarioView(ActionEvent event) {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Cadastro_Funcionario.fxml").toUri().toURL());
			
			funcionarioPane.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void loadAdministradorView(ActionEvent event) {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Administrador.fxml").toUri().toURL());
			
			funcionarioPane.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
