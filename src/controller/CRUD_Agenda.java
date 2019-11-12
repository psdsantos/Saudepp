package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
	private DatePicker datePicker;
	@FXML
	private TableView<Consulta> tableConsulta;
    @FXML 
    private TableColumn<Paciente, String> dateCol;
    @FXML 
    private TableColumn<Paciente, String> pacCol;
    @FXML 
    private TableColumn<Paciente, String> medCol;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		dateCol.setCellValueFactory(new PropertyValueFactory<>("data"));
		pacCol.setCellValueFactory(new PropertyValueFactory<>("paciente")); 	
		medCol.setCellValueFactory(new PropertyValueFactory<>("medico")); 
	                
		refreshTableView();
		
		// TO DO 
		//setGlobalEventHandler(); // ENTER TYPED          
		
	}
	
	private void refreshTableView() {
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
			cons.setPaciente(rSet.getString("paciente"));
			cons.setData(DateHandling.toMilitaryFormat(rSet.getDate("data")));
			cons.setMedico(rSet.getString("medico"));
			obs.add(cons);
		}
		DB.closeResultSet(rSet);
		return obs;
	}
	
	@FXML
	private void loadAtualizar_ConsultaView(ActionEvent event) {
		try {
			
			Parent root;
			root = FXMLLoader.<BorderPane>load(Paths.get("src/view/Atualizar_Consulta.fxml").toUri().toURL());
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
	
	
	@FXML
	private void loadCadastro_ConsultaView(ActionEvent event) {
		try {
			
			Parent root;
			root = FXMLLoader.<BorderPane>load(Paths.get("src/view/Cadastro_Consulta.fxml").toUri().toURL());
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
	
	@FXML
	private void loadAgendaView(ActionEvent event) { // VOLTAR
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Agenda_Atendente.fxml").toUri().toURL());
			
			agendaPane.getChildren().clear();
			agendaPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
