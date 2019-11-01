package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.entities.Funcionario;
import src.MaskedTextField;

public class Atualizar_Funcionario implements Initializable {
	@FXML
	private BorderPane cadastroFuncionarioPane;
	@FXML
	private Button closeButton;
	@FXML
	private Button saveButton;
	@FXML
	private TextField nome;
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
	private Funcionario fun;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		/*
		dados.add(cpf.setPlainText());
		if(email.getText() != null) dados.add(email.getText());
		dados.add(dataNasc.setValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dados.add(endereco.setText());
		dados.add(cep.setPlainText());
		if(cel.setPlainText() != null) dados.add(cel.setPlainText());
		if(tel.setPlainText() != null) dados.add(tel.setPlainText());
		*/
		System.out.println(fun);
	}
	
	public void initVariable(Funcionario fun){
		this.fun = fun;
    }
	
	@FXML
	private void fill() {
		System.out.println(fun);
		nome.setText(fun.getNome());
        email.setText(fun.getEmail());

	}
	
	@FXML
	private void updateData(ActionEvent event) {
		try {
			
			closeView();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
