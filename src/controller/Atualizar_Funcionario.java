package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import model.entities.Funcionario;
import model.exceptions.InvalidFieldSizeException;
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
	@FXML
	private RadioButton radioButtonMedico;
	@FXML
	private RadioButton radioButtonAtendente;
	@FXML
	private MaskedTextField crm;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void enableCrm() {
		crm.setDisable(false);
	}
	@FXML
	private void disableCrm() {
		crm.setDisable(true);
	}
	
	public void initVariable(Funcionario fun){
		this.fun = fun;
		fill();
    }
	
	@FXML
	private void fill() {
		
		nome.setText(fun.getNome());
		cpf.setPlainText(fun.getCpf());
		endereco.setText(fun.getEndereco());
		cep.setPlainText(fun.getCep());
		cel.setPlainText(fun.getCelular());
		tel.setPlainText(fun.getTelefone());
        email.setText(fun.getEmail());
        //crm.setText(med.getCrm());
        
	}
	
	public static final LocalDate toLocalDate (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
	
	@FXML
	private void updateData(ActionEvent event) {
		try {
			
			if(nome.getText().length() == 0) throw new InvalidFieldSizeException();
			if(cpf.getPlainText().length() != 11) throw new InvalidFieldSizeException();
			if(email.getText().length() == 0) throw new InvalidFieldSizeException();
			if(endereco.getText().length() == 0) throw new InvalidFieldSizeException();
			if(cep.getPlainText().length() != 9) throw new InvalidFieldSizeException();
			
			List<String> dados = new ArrayList<String>();
			
			dados.add(nome.getText());
			dados.add(cpf.getPlainText());
			if(cpf.getPlainText() == null) throw new NullPointerException();
			if(email.getText() != null) dados.add(email.getText());
			dados.add(dataNasc.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			dados.add(endereco.getText());
			dados.add(cep.getPlainText());
			if(cel.getPlainText() != null) dados.add(cel.getPlainText());
			if(tel.getPlainText() != null) dados.add(tel.getPlainText());
			
			List<String> columns = new ArrayList<String>();
			
			columns.add("nome");
			columns.add("cpf");
			columns.add("email");
			columns.add("datanasc");
			columns.add("endereco");
			columns.add("cep");
			columns.add("celular");
			columns.add("telefone");
			
			if(radioButtonMedico.isSelected()) {
				if(crm.getPlainText().length() < 4 || crm.getPlainText().length() > 9) throw new InvalidFieldSizeException();
				columns.add("crm");
				dados.add(crm.getText());
				DB.updateData("medico", columns, dados, "codMedico", fun.getCodFuncionario().toString());
			} else if(radioButtonAtendente.isSelected()) {
				DB.updateData("funcionario", columns, dados, "codFuncionario", fun.getCodFuncionario().toString());
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
            alert.setContentText("Obrigatório preenchimento completo de todas as informações marcadas com asterisco (*).");
            alert.showAndWait();
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
