package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.db.DB;
import model.entities.Consulta;
import model.entities.Paciente;
import model.util.DateHandling;
import model.util.WindowsParam;

public class CRUD_Agenda implements Initializable {

	@FXML
	private BorderPane agendaPane;
	@FXML
	private DatePicker searchDate;
	@FXML
	private TableView<Consulta> tableConsulta;
    @FXML 
    private TableColumn<Paciente, String> dateCol;
    @FXML 
    private TableColumn<Paciente, String> pacCol;
    @FXML 
    private TableColumn<Paciente, String> medCol;
    @FXML 
    private TextField selectedPac2;
    @FXML 
    private TextField selectedPac1;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateHandling.toMilitaryFormat(searchDate);
		
		dateCol.setCellValueFactory(new PropertyValueFactory<>("data"));
		pacCol.setCellValueFactory(new PropertyValueFactory<>("paciente")); 	
		medCol.setCellValueFactory(new PropertyValueFactory<>("medico")); 
	                
		refreshTableView();
		
		setEnterTypedHandler(searchDate); 
	}
	
	private void setEnterTypedHandler(Node root) {
	    root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	        	searchDate();
	        	showPac();
	        	ev.consume(); 
	        }
	    });
	}
	
	@FXML
	private void searchDate() {
		
		String search = searchDate.getValue().toString();
		tableConsulta.getItems().stream().filter(item -> item.getData().toString().contains(search))
		.findAny().ifPresent(item -> {
			tableConsulta.getSelectionModel().select(item);
			tableConsulta.scrollTo(item);
	    });
	}
	
	@FXML
	private void deleteData(ActionEvent event) throws SQLException {
		
		Consulta consulta = tableConsulta.getSelectionModel().getSelectedItem();
		Integer id = consulta.getCodConsulta();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText("Você tem certeze que deseja desmarcar a consulta selecionada?");
		alert.setContentText("Paciente: " + consulta.getPaciente() + "\nMédico: " + consulta.getMedico() + 
				"\nData: " + consulta.getData());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			DB.deleteData("consulta", "codconsulta", id.toString());
			
			refreshTableView();
		} 
		
	}
	
	@FXML
	private void showPac() {
		try {
			Consulta consulta = tableConsulta.getSelectionModel().getSelectedItem();
			String paciente = consulta.getPaciente();
			selectedPac1.setText(paciente);
			selectedPac2.setText(paciente);
		} catch (Exception e) {
		}
	}
	
	public void refreshTableView() {
		try {
			tableConsulta.getItems().setAll(initList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ObservableList<Consulta>  initList() throws SQLException {
		ObservableList<Consulta> obs = FXCollections.observableArrayList();
		ResultSet rSet = DB.showEntity("consulta");
		while(rSet.next()) {
			Consulta cons = new Consulta();
			cons.setCodConsulta(Integer.parseInt(rSet.getString("codconsulta")));
			ResultSet rSetConsultaPac = DB.consultation("paciente", rSet.getString("data"));
			while(rSetConsultaPac.next()) {
				cons.setPaciente(rSetConsultaPac.getString("nome"));
			}
			DB.closeResultSet(rSetConsultaPac);
			cons.setData(DateHandling.toMilitaryFormat(rSet.getDate("data")));
			ResultSet rSetConsultaFunc = DB.consultation("funcionario", rSet.getString("data"));
			while(rSetConsultaFunc.next()) {
				cons.setMedico(rSetConsultaFunc.getString("nome"));
			}
			DB.closeResultSet(rSetConsultaFunc);
			obs.add(cons);
		}
		DB.closeResultSet(rSet);
		return obs;
	}
	
	@FXML
	private void loadAtualizar_ConsultaView(ActionEvent event) {
		try {
			
			Parent root;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Atualizar_Consulta.fxml"));
            root = loader.load();
            Atualizar_Consulta controller = loader.<Atualizar_Consulta>getController();
            controller.setController(this);
            controller.initConsulta(tableConsulta.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Saúde ++");
            stage.getIcons().add(new Image("model/resources/saudeIcon.png"));
            stage.setScene(new Scene(root, 505, WindowsParam.getHeight()));
            stage.setResizable(false);
            stage.show();
			
		} catch (NullPointerException e) {
	 		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("AVISO");
			alert.setHeaderText("Selecione ao menos 1 (uma) consulta na tabela.");
			alert.showAndWait();
			e.printStackTrace();
			
	 	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void loadCadastro_ConsultaView(ActionEvent event) {
		try {
			
			Parent root;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Cadastro_Consulta.fxml"));
            root = loader.load();
            Cadastro_Consulta controller = loader.<Cadastro_Consulta>getController();
            controller.setController(this);
            Stage stage = new Stage();
            stage.setTitle("Saúde ++");
            stage.getIcons().add(new Image("model/resources/saudeIcon.png"));
            stage.setScene(new Scene(root, 505, WindowsParam.getHeight()));
            stage.setResizable(false);
            stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// VOLTAR
	@FXML
	private void loadAgendaView(ActionEvent event) { 
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Atendente.fxml").toUri().toURL());
			
			agendaPane.getChildren().clear();
			agendaPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
