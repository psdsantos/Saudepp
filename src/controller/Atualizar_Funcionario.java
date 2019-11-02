package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		
	}
	
	public void initVariable(Funcionario fun){
		this.fun = fun;
		fill();
    }
	
	@FXML
	private void fill() {
		
		nome.setText(fun.getNome());
		//dataNasc.setValue((fun.getdataNasc());
		cpf.setText(fun.getCpf());
		endereco.setText(fun.getEndereco());
		cep.setText(fun.getCep());
		cel.setText(fun.getCelular());
		tel.setText(fun.getTelefone());
        email.setText(fun.getEmail());
        
        
	}
	
	public static final LocalDate toLocalDate (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
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
