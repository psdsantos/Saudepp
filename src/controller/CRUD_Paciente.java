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
import model.entities.Paciente;
import model.util.WindowsParam;
import src.MaskedTextField;

public class CRUD_Paciente implements Initializable {

	@FXML
	private BorderPane pacientePane;
	@FXML 
	private TableView<Paciente> tablePaciente;
	@FXML 
	private TableColumn<Paciente, Integer> codCol;
    @FXML 
    private TableColumn<Paciente, String> nomeCol;
    @FXML 
    private TableColumn<Paciente, String> crmCol;
    @FXML 
    private TableColumn<Paciente, String> cpfCol;
    @FXML 
    private MaskedTextField selectedCpf1;
    @FXML 
    private MaskedTextField selectedCpf2;
    @FXML
    private TextField searchBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		codCol.setCellValueFactory(new PropertyValueFactory<>("codPaciente")); 	
		cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf")); 
	                
		refreshTableView();
	
		setGlobalEventHandler(searchBar); // ENTER TYPED          
	                
	}
	
	private void setGlobalEventHandler(Node root) {
	    root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           searchClick();
	           ev.consume(); 
	        }
	    });
	}
	
	@FXML
	private void searchClick() {
		/*//search by id
		int searchId = Integer.parseInt(searchBar.getText());
		tablePaciente.getItems().stream().filter(item -> item.getCodPaciente() == searchId).findAny()
		.ifPresent(item -> {
	        tablePaciente.getSelectionModel().select(item);
	        tablePaciente.scrollTo(item);
	         showCpf();
	    });
		*/
		
		//search by name
		String searchName = searchBar.getText().toLowerCase();
		tablePaciente.getItems().stream().filter(item -> item.getNome().toLowerCase().startsWith(searchName))
		.findAny().ifPresent(item -> {
	        tablePaciente.getSelectionModel().select(item);
	        tablePaciente.scrollTo(item);
	        showCpf();
	    });
		
	}
	
	public void refreshTableView() {
		try {
			tablePaciente.getItems().setAll(initList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void deleteData(ActionEvent event) throws SQLException {
		
		Paciente pac = tablePaciente.getSelectionModel().getSelectedItem();
		String id = pac.getCodPaciente().toString();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText("Você tem certeze que deseja deletar o Paciente selecionado?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			DB.deleteData("Paciente", "codPaciente", id);
			
			refreshTableView();
		} 
		
	}
	
	@FXML
	private void showCpf() {
		try {
			Paciente pac = tablePaciente.getSelectionModel().getSelectedItem();
			String cpf = pac.getCpf();
			cpf = String.format("%s.%s.%s-%s", cpf.substring(0, 3), 
					cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
			selectedCpf1.setText(cpf);
			selectedCpf2.setText(cpf);
		} catch (Exception e) {
			
		}
	}
	
	private ObservableList<Paciente>  initList() throws SQLException {
		ObservableList<Paciente> obs = FXCollections.observableArrayList();
		ResultSet rSet = DB.showEntity("Paciente");
		while(rSet.next()) {
			Paciente pac = new Paciente();
			pac.setCodPaciente(Integer.parseInt(rSet.getString("codPaciente")));
			pac.setNome(rSet.getString("nome"));
			pac.setCpf(rSet.getString("cpf"));
			pac.setdataNasc(rSet.getDate("datanasc"));
			pac.setEndereco(rSet.getString("endereco"));
			pac.setCep(rSet.getString("cep"));
			pac.setCelular(rSet.getString("celular"));
			pac.setTelefone(rSet.getString("telefone"));
			pac.setEmail(rSet.getString("email"));
			obs.add(pac);
		}
		DB.closeResultSet(rSet);
		return obs;
	}
	
	
	
	
	
	@FXML
	private void loadCadastro_PacienteView(ActionEvent event) {
		try {
			
			Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Cadastro_Paciente.fxml"));
            root = loader.load();
            Cadastro_Paciente controller = loader.<Cadastro_Paciente>getController();
            controller.setController(this);
            Stage stage = new Stage();
            stage.setTitle("Saúde ++");
            stage.getIcons().add(new Image("model/resources/saudeIcon.png"));
            stage.setScene(new Scene(root));
            stage.setHeight(WindowsParam.getHeight());
            stage.setResizable(true);
            stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void loadAtualizar_PacienteView(ActionEvent event) {
		try {
			
			Parent root;
			Paciente pac = tablePaciente.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Atualizar_Paciente.fxml"));
            root = loader.load();
            Atualizar_Paciente controller = loader.<Atualizar_Paciente>getController();
            controller.initPac(pac);
            controller.setController(this);
            Stage stage = new Stage();
            stage.setTitle("Saúde ++");
            stage.getIcons().add(new Image("model/resources/saudeIcon.png"));
            stage.setScene(new Scene(root));
            stage.setHeight(WindowsParam.getHeight());
            stage.setResizable(true);
            stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void loadAtendenteView(ActionEvent event) {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Atendente.fxml").toUri().toURL());
			
			pacientePane.getChildren().clear();
			pacientePane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
