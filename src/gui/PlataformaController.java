package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.PlataformaModel;

public class PlataformaController implements Initializable{
	@FXML
	private Button btnEnviar;
	
	@FXML
	private TextField txtNome;
	
	public void onSubmitPlataforma() {
		boolean valid = false;
		if(txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			txtNome.setStyle("-fx-border-color: red");
			valid = false;
		} else {
			txtNome.setStyle("-fx-border-color: #d2d2d2");
			valid = true;
		}
		
		if(valid) {
			try {
				if(PlataformaModel.insertPlataformaBD(txtNome.getText())) {
					Alert info = new Alert(Alert.AlertType.INFORMATION);
					info.setTitle("Gerenciador de Registros");
					info.setHeaderText("Cadastro Efetivado");
					info.setContentText("Novo plataforma inserida com sucesso! ");
					info.showAndWait();
					MainController.getStg().close();
				} 
			} catch (SQLException e) { 
				Alert info = new Alert(Alert.AlertType.WARNING);
				info.setTitle("Gerenciador de Registros");
				info.setHeaderText("Cadastro Nao Efetivado");
				info.setContentText(e.getMessage());
				info.showAndWait();
			}
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldMaxLength(txtNome, 45);
		
	}
}
