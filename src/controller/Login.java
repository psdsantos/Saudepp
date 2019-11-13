package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Login implements Initializable {
	@FXML
	private BorderPane LoginPane;
	@FXML
	private PasswordField passField;
	@FXML
	private TextField userField;	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setEnterTypedHandler(passField); 
		setEnterTypedHandler(userField); 
	}
	
	private void setEnterTypedHandler(Node root) {
	    root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	        	verifyLogin();
	        	ev.consume(); 
	        }
	    });
	}
	
	@FXML
	private void verifyLogin() {
		if(userField.getText().equalsIgnoreCase("admin") && passField.getText().equals("admin123")) {
			loadAdministradorView();
		}else if(userField.getText().equalsIgnoreCase("atend") && passField.getText().equals("atend123")) {
			loadAtendenteView();
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("AVISO");
			alert.setHeaderText("Erro no login");
			alert.setContentText("O nome de usuário ou senha informados não existe(m) na base de dados");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void loadAdministradorView() {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Administrador.fxml").toUri().toURL());
			
			LoginPane.getChildren().clear();
			LoginPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void loadAtendenteView() {
		try {
			
			BorderPane pane = new BorderPane();
			pane = FXMLLoader.<BorderPane>load(Paths.get("src/view/Atendente.fxml").toUri().toURL());
			
			LoginPane.getChildren().clear();
			LoginPane.setCenter(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
