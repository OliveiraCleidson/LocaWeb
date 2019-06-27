package gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.ClienteModel;

public class ClienteController implements Initializable {
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtRg;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private VBox vboxAvisos;
	
	public void onSubmitCliente() {
		boolean valid[] = new boolean[5];
		for (boolean b : valid) {
			b = false;
		}
		if(txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			txtNome.setStyle("-fx-border-color: red");
			valid[0] = false;
		} else {
			txtNome.setStyle("-fx-border-color: #d2d2d2");
			valid[0] = true;
		}
		
		if(txtRg.getText() == null || txtRg.getText().trim().equals("")) {
			txtRg.setStyle("-fx-border-color: red");
			valid[1] = false;
		} else {
			txtRg.setStyle("-fx-border-color: #d2d2d2");
			valid[1] = true;
		}
		
		if(txtCpf.getText() == null || txtCpf.getText().trim().equals("")) {
			txtCpf.setStyle("-fx-border-color: red");
			valid[2] = false;
		} else {
			txtCpf.setStyle("-fx-border-color: #d2d2d2");
			valid[2] = true;
		}
		
		if(txtEmail.getText() == null || txtEmail.getText().trim().equals("")) {
			txtEmail.setStyle("-fx-border-color: red");
			valid[3] = false;
		} else {
			txtEmail.setStyle("-fx-border-color: #d2d2d2");
			valid[3] = true;
		}
		
		if(txtTelefone.getText() == null || txtTelefone.getText().trim().equals("")) {
			txtTelefone.setStyle("-fx-border-color: red");
			valid[4] = false;
		} else {
			txtTelefone.setStyle("-fx-border-color: #d2d2d2");
			valid[4] = true;
		}
		boolean validation = true;
		for(boolean b : valid) {
			if(b == false)
				validation = false;
		}
		
		if(validation) {
			try { 
				if(ClienteModel.insertClienteBD(txtNome.getText(), txtRg.getText(), txtCpf.getText(), txtEmail.getText(), txtTelefone.getText())) {
					Alert info = new Alert(Alert.AlertType.INFORMATION);
					info.setTitle("Gerenciador de Registros");
					info.setHeaderText("Cadastro Efetivado");
					info.setContentText("Novo cliente inserido com sucesso! ");
					info.showAndWait();
					MainController.prop.updateTableView();
					MainController.getStg().close();;
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
		Constraints.setTextFieldInteger(txtRg);
		Constraints.setTextFieldInteger(txtCpf);
		Constraints.setTextFieldInteger(txtTelefone);
		Constraints.setTextFieldMaxLength(txtNome, 45);
		Constraints.setTextFieldMaxLength(txtEmail, 60);
	}
}
