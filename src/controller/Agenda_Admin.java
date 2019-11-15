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
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import model.db.DB;
import model.entities.Consulta;
import model.entities.Paciente;
import model.util.DateHandling;

public class Agenda_Admin implements Initializable {

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
    @FXML
    private TextField searchPac;
    @FXML
    private TextField searchMed;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	               
		DateHandling.toMilitaryFormat(datePicker);
		
		dateCol.setCellValueFactory(new PropertyValueFactory<>("data"));
		pacCol.setCellValueFactory(new PropertyValueFactory<>("paciente")); 	
		medCol.setCellValueFactory(new PropertyValueFactory<>("medico")); 
		
		try {
			tableConsulta.getItems().setAll(initList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setEnterTypedHandler(searchPac); 
		setEnterTypedHandler(searchMed); 
		setEnterTypedHandler(datePicker); 
	}
	
	private void setEnterTypedHandler(Node root) {
	    root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	        	if(root.getId().equals("searchMed")) searchMed();
	        	if(root.getId().equals("searchPac")) searchPac();
	        	if(root.getId().equals("datePicker")) searchDate();
	        	ev.consume(); 
	        }
	    });
	}
	
	@FXML
	private void searchMed() {
		
		String searchName = searchMed.getText().toLowerCase();
		tableConsulta.getItems().stream().filter(item -> item.getMedico().toLowerCase().startsWith(searchName))
		.findAny().ifPresent(item -> {
			tableConsulta.getSelectionModel().select(item);
			tableConsulta.scrollTo(item);
	    });
	}
	
	@FXML
	private void searchPac() {
		
		String searchName = searchPac.getText().toLowerCase();
		tableConsulta.getItems().stream().filter(item -> item.getPaciente().toLowerCase().startsWith(searchName))
		.findAny().ifPresent(item -> {
			tableConsulta.getSelectionModel().select(item);
			tableConsulta.scrollTo(item);
	    });
	}
	
	@FXML
	private void searchDate() {
		
		String search = datePicker.getValue().toString();
		tableConsulta.getItems().stream().filter(item -> item.getData().toString().contains(search))
		.findAny().ifPresent(item -> {
			tableConsulta.getSelectionModel().select(item);
			tableConsulta.scrollTo(item);
	    });
	}
	
	private ObservableList<Consulta>  initList() throws SQLException {
		ObservableList<Consulta> obs = FXCollections.observableArrayList();
		ResultSet rSet = DB.showEntity("consulta");
		while(rSet.next()) {
			Consulta cons = new Consulta();
			cons.setCodConsulta(rSet.getInt("codconsulta"));
			cons.setcodpaciente(rSet.getInt("codpaciente"));
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
			cons.setData(DateHandling.toMilitaryFormat(rSet.getDate("data")));
			cons.setcodmedico(rSet.getInt("codfuncionario"));
			obs.add(cons);
		}
		DB.closeResultSet(rSet);
		return obs;
	}
	
	@FXML
	private void loadAdministradorView(ActionEvent event) {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Administrador.fxml").toUri().toURL());
			
			agendaPane.getChildren().clear();
			agendaPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
