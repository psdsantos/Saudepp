package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.db.DB;
import model.entities.Funcionario;
import model.entities.Paciente;
import model.exceptions.InvalidFieldSizeException;
import model.util.DateHandling;

public class Cadastro_Consulta implements Initializable {
	@FXML
	private BorderPane cadastroConsultaPane;
	@FXML
	private Button closeButton;
	@FXML
	private TableView<Paciente> pacTable;
	@FXML
	private TableView<Funcionario> medTable;
	@FXML
	private TableColumn<Funcionario, String> medCol;
	@FXML
	private TableColumn<Funcionario, String> crmCol;
	@FXML
	private TableColumn<Paciente, String> pacCol;
	@FXML
	private TableColumn<Paciente, String> cpfCol;
	@FXML
	private DatePicker datePicker;
	private CRUD_Agenda controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateHandling.toMilitaryFormat(datePicker);
		
		pacCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		medCol.setCellValueFactory(new PropertyValueFactory<>("nome")); 	
		cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		crmCol.setCellValueFactory(new PropertyValueFactory<>("crm"));
		
		try {
			pacTable.getItems().setAll(initPac());
			medTable.getItems().setAll(initMed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ObservableList<Paciente>  initPac() throws SQLException {
		ObservableList<Paciente> obs = FXCollections.observableArrayList();
		ResultSet rSet = DB.showEntity("Paciente");
		while(rSet.next()) {
			Paciente pac = new Paciente();
			pac.setNome(rSet.getString("nome"));
			pac.setCpf(rSet.getString("cpf"));
			obs.add(pac);
		}
		DB.closeResultSet(rSet);
		return obs;
	}
	
	private ObservableList<Funcionario>  initMed() throws SQLException {
		ObservableList<Funcionario> obs = FXCollections.observableArrayList();
		ResultSet rSet = DB.showEntity("Funcionario");
		while(rSet.next()) {
			Funcionario med = new Funcionario();
			med.setNome(rSet.getString("nome"));
			med.setCrm(rSet.getString("crm"));
			if(med.getCrm() != null) obs.add(med);
		}
		DB.closeResultSet(rSet);
		return obs;
	}
	
	@FXML
	private void saveData(ActionEvent event) {
		try {

			if(pacTable.getSelectionModel() == null) throw new InvalidFieldSizeException();
			if(medTable.getSelectionModel() == null) throw new InvalidFieldSizeException();
			if(datePicker.getValue() == null) throw new InvalidFieldSizeException();

			List<String> dados = new ArrayList<String>();
			dados.add(pacTable.getSelectionModel().getSelectedItem().getNome());
			dados.add(medTable.getSelectionModel().getSelectedItem().getNome());
			dados.add(datePicker.getValue().toString());
			
			List<String> columns = new ArrayList<String>();
			columns.add("paciente");
			columns.add("medico");
			columns.add("data");
			
			DB.insertData("consulta", columns, dados);
			
			closeView();
			
		} catch(InvalidFieldSizeException e) {
			Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Erro no preenchimento de dados");
            alert.setContentText("Obrigatório selecionar o médico, o paciente e a data da consulta a ser agendada.");
            alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setController(CRUD_Agenda controller) {
		this.controller = controller;
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
