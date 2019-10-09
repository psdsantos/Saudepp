package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class CRUD_Funcionario implements Initializable {

	@FXML
	private AnchorPane funcionarioPane;
	private RadioButton radioButtonAtendente;
	private RadioButton radioButtonMedico;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ToggleGroup group = new ToggleGroup();
		radioButtonMedico.setToggleGroup(group);
	    radioButtonAtendente.setToggleGroup(group);
	}
	
	@FXML
	private void loadCadastro_FuncionarioView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			pane = FXMLLoader.<AnchorPane>load(Paths.get("src/view/Cadastro_Funcionario.fxml").toUri().toURL());
			
			funcionarioPane.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
