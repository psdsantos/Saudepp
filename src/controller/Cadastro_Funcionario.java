package controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.db.DB;
import src.MaskedTextField;

public class Cadastro_Funcionario implements Initializable {
	@FXML
	private BorderPane cadastroFuncionarioPane;
	@FXML
	private Button closeButton;
	@FXML
	private TextField nome;
	@FXML
	private TextField crm;
	@FXML
	private MaskedTextField cpf;
	@FXML
	private DatePicker dataNasc;
	@FXML
	private TextArea endereco;
	@FXML
	private MaskedTextField cep;
	@FXML
	private MaskedTextField cel;
	@FXML
	private MaskedTextField tel;
	@FXML
	private TextField email;
	@FXML
	private RadioButton radioButtonMedico;
	@FXML
	private RadioButton radioButtonAtendente;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void saveData(ActionEvent event) {
		try {
			
			List<String> dados = new ArrayList<String>();
			
			// [ARMENGUE] USAR AUTO_INCREMENT
			Random gerador = new Random();
			Integer a = gerador.nextInt(1000);
			String codFun = a.toString();
			
			dados.add(codFun);
			
			dados.add(nome.getText());
			if(radioButtonMedico.isSelected())dados.add(crm.getText());
			dados.add(cpf.getPlainText());
			if(email.getText() != null) dados.add(email.getText());
			dados.add(dataNasc.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			dados.add(endereco.getText());
			dados.add(cep.getPlainText());
			if(cel.getPlainText() != null) dados.add(cel.getPlainText());
			if(tel.getPlainText() != null) dados.add(tel.getPlainText());
			
			if(radioButtonAtendente.isSelected()) {
				DB.insertData("funcionario", dados);
			} else if (radioButtonMedico.isSelected()) {
				DB.insertData("medico", dados);
			}
			
			closeView();
			
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("AVISO");
			alert.setHeaderText("Obrigat�rio preenchimento de todas as informa��es marcadas com asterisco (*).");
			//alert.setContentText("Careful with the next step!");

			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void enableCrm() {
		crm.setDisable(false);
	}
	@FXML
	private void disableCrm() {
		crm.setDisable(true);
	}
	
	@FXML
	private void closeView() {
		try {
			
		    Stage stage = (Stage) closeButton.getScene().getWindow();
		    stage.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
