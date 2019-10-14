package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.util.WindowsParam;

public class CRUD_Agenda implements Initializable {

	@FXML
	private BorderPane agendaPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	               
	                
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
