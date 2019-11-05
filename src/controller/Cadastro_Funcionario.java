package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.db.DB;
import model.exceptions.InvalidFieldSizeException;
import model.util.DateHandling;
import src.MaskedTextField;

public class Cadastro_Funcionario implements Initializable {
	@FXML
	private BorderPane cadastroFuncionarioPane;
	@FXML
	private Button closeButton;
	@FXML
	private TextField nome;
	@FXML
	private MaskedTextField crm;
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
	private CRUD_Funcionario controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateHandling.toMilitaryFormat(dataNasc);
	}
	
	public void setController(CRUD_Funcionario controller) {
		this.controller = controller;
	}
	
	@FXML
	private void saveData(ActionEvent event) {
		try {
			
			List<String> dados = new ArrayList<String>();
			
			// [ARMENGUE] USAR AUTO_INCREMENT
			
			if(nome.getText().length() == 0) throw new InvalidFieldSizeException();
			if(cpf.getPlainText().length() != 11) throw new InvalidFieldSizeException();
			if(endereco.getText().length() == 0) throw new InvalidFieldSizeException();
			if(cep.getPlainText().length() != 8) throw new InvalidFieldSizeException();
			
			Random gerador = new Random();
			Integer a = gerador.nextInt(1000);
			String codFun = a.toString();
			
			dados.add(codFun);
		
			dados.add(nome.getText());
			if(radioButtonMedico.isSelected())dados.add(crm.getText());
			dados.add(cpf.getPlainText());
			if(email.getText() != null) dados.add(email.getText());
			dados.add(dataNasc.getValue().toString());
			dados.add(endereco.getText());
			dados.add(cep.getPlainText());
			if(cel.getPlainText() != null) dados.add(cel.getPlainText());
			if(tel.getPlainText() != null) dados.add(tel.getPlainText());
			
			if(radioButtonAtendente.isSelected()) {
				DB.insertData("funcionario", dados);
			} else if (radioButtonMedico.isSelected()) {
				if(crm.getPlainText().length() < 4 || crm.getPlainText().length() > 9) throw new InvalidFieldSizeException();
				DB.insertData("medico", dados);
			}
			
			closeView();
			
		} catch(InvalidFieldSizeException e) {
			Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Erro no preenchimento de dados");
            alert.setContentText("Obrigatório preenchimento completo de todas as informações marcadas com asterisco (*).");
            alert.showAndWait();
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("AVISO");
			alert.setHeaderText("Erro no preenchimento de dados");
			alert.setContentText("Obrigatório preenchimento de todas as informações marcadas com asterisco (*).");
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
			controller.refreshTableView();
		    Stage stage = (Stage) closeButton.getScene().getWindow();
		    stage.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
