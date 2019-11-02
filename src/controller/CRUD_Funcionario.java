package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.db.DB;
import model.entities.Funcionario;
import model.util.WindowsParam;
import src.MaskedTextField;

public class CRUD_Funcionario implements Initializable {

	@FXML
	private BorderPane funcionarioPane;
	@FXML 
	private TableView<Funcionario> tableFuncionario;
	@FXML 
	private TableColumn<Funcionario, Integer> codCol;
    @FXML 
    private TableColumn<Funcionario, String> nomeCol;
    @FXML 
    private TableColumn<Funcionario, String> cpfCol;
    @FXML 
    private MaskedTextField selectedCpf1;
    @FXML 
    private MaskedTextField selectedCpf2;
    @FXML
    private TextField searchBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		codCol.setCellValueFactory(new PropertyValueFactory<>("codFuncionario")); 	
		cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf")); 
	                
		refreshTableView();
	
		
	}
	
	@FXML
	private void onEnter(KeyEvent event) {
		searchBar.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	                System.out.println("foi");
	            }
	        }
	    });
	}
	
	@FXML
	private void onAction(ActionEvent event) {
		
		System.out.println("foi");
	}
	
	private void refreshTableView() {
		try {
			tableFuncionario.getItems().setAll(initList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void deleteData(ActionEvent event) throws SQLException {
		
		Funcionario fun = tableFuncionario.getSelectionModel().getSelectedItem();
		String id = fun.getCodFuncionario().toString();
		
		DB.deleteData("funcionario", "codFuncionario", id);
		
		refreshTableView();
	}
	
	@FXML
	private void showCpf(MouseEvent event) {
		
			Funcionario fun = tableFuncionario.getSelectionModel().getSelectedItem();
			String cpf = fun.getCpf();
			cpf = String.format("%s.%s.%s-%s", cpf.substring(0, 3), 
					cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
			selectedCpf1.setText(cpf);
			selectedCpf2.setText(cpf);
		
	}
	
	private ObservableList<Funcionario>  initList() throws SQLException {
		ObservableList<Funcionario> obs = FXCollections.observableArrayList();
		ResultSet rSet = DB.showEntity("funcionario");
		while(rSet.next()) {
			Funcionario fun = new Funcionario();
			fun.setCodFuncionario(Integer.parseInt(rSet.getString("codFuncionario")));
			fun.setNome(rSet.getString("nome"));
			fun.setCpf(rSet.getString("cpf"));
			fun.setdataNasc(toDate(rSet.getString("datanasc")));
			fun.setEndereco(rSet.getString("endereco"));
			fun.setCep(rSet.getString("cep"));
			fun.setCelular(rSet.getString("celular"));
			fun.setTelefone(rSet.getString("telefone"));
			fun.setEmail(rSet.getString("email"));
			obs.add(fun);
		}
		DB.closeResultSet(rSet);
		return obs;
	}
	
	private Date toDate(String date) {
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date1;
	}
	@FXML
	private void loadAtualizar_FuncionarioView(ActionEvent event) {
		
		try {
			Parent root;
			
    		Funcionario fun = tableFuncionario.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Atualizar_Funcionario.fxml"));
            root = loader.load();
            //root = FXMLLoader.<BorderPane>load(Paths.get("src/view/Atualizar_Funcionario.fxml").toUri().toURL());
            Atualizar_Funcionario controller = loader.<Atualizar_Funcionario>getController();
		 	
            Stage stage = new Stage();
            stage.setTitle("Saude ++");
            stage.getIcons().add(new Image("model/resources/saudeIcon.png"));
            stage.setScene(new Scene(root));
            stage.setHeight(WindowsParam.getHeight());
            stage.setResizable(true);
            
		 	stage.show();
		 	controller.initVariable(fun);
		 	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void loadCadastro_FuncionarioView(ActionEvent event) {
		try {
			
			Parent root;
			root = FXMLLoader.<BorderPane>load(Paths.get("src/view/Cadastro_Funcionario.fxml").toUri().toURL());
            Stage stage = new Stage();
            stage.setTitle("Saúde ++");
            stage.getIcons().add(new Image("model/resources/saudeIcon.png"));
            stage.setScene(new Scene(root));
            stage.setHeight(WindowsParam.getHeight());
            stage.setResizable(true);
            stage.show();
			
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

        	    @Override
        	    public void handle(WindowEvent paramT) {
        	        refreshTableView();
        	    }
        	});
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@FXML
	private void loadAdministradorView(ActionEvent event) {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Administrador.fxml").toUri().toURL());
			
			funcionarioPane.getChildren().clear();
			funcionarioPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
