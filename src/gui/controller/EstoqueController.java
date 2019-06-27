package gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.EstoqueModel;
import model.JogoModel;
import model.PlataformaModel;

public class EstoqueController implements Initializable {
	@FXML
	private ComboBox<JogoModel> cbJogo;
	@FXML
	private ComboBox<PlataformaModel> cbPlataforma;
	@FXML
	private TextField txtPreco;
	@FXML
	private TextField txtQtd;
	@FXML
	private Button btnAtualizar;

	private ObservableList<JogoModel> obsJogos;
	private ObservableList<PlataformaModel> obsPlataformas;
	
	private EstoqueModel estoque;
	
	private JogoModel jgSelect;
	private PlataformaModel pfSelect;
	
	public void onComboBoxjogosActio() { 
		jgSelect = (JogoModel) cbJogo.getSelectionModel().getSelectedItem();
		onComboBoxChange();
	}
	
	public void onComboBoxPlatActio() { 
		pfSelect = (PlataformaModel) cbPlataforma.getSelectionModel().getSelectedItem();
		onComboBoxChange();
	}
	
	public void onComboBoxChange() {
		estoque = EstoqueModel.getEstoqueDAO().findByIdJP(jgSelect.getId(), pfSelect.getId());
		txtPreco.setText(Double.toString(estoque.getPreco()));
		txtQtd.setText(Integer.toString(estoque.getQuantidade()));
	}
	
	public void onBtnAtualizarAction() {
		try {
			estoque.setPreco(Double.parseDouble(txtPreco.getText()));
			estoque.setQuantidade(Integer.parseInt(txtQtd.getText()));
			if(EstoqueModel.getEstoqueDAO().update(estoque)) {
				Alert info = new Alert(Alert.AlertType.INFORMATION);
				info.setTitle("Gerenciador de Estoque");
				info.setHeaderText("Atualizacao Efetuada");
				info.setContentText("O Estoque foi atualizado com sucesso! ");
				info.showAndWait();
				MainController.prop.updateTableView();
			} 
		} catch (SQLException e) { 
			Alert info = new Alert(Alert.AlertType.WARNING);
			info.setTitle("Gerenciador de Estoque");
			info.setHeaderText("Erro na Atualizacao");
			info.setContentText(e.getMessage());
			info.showAndWait();
		}
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<JogoModel> jogos = JogoModel.getJogoDAO().findByAll();
		List<PlataformaModel> plataformas = PlataformaModel.getPlataformaDAO().findByAll();

		if (jogos != null) {
			obsJogos = FXCollections.observableArrayList(jogos);
			cbJogo.setItems(obsJogos);

		}

		if (plataformas != null) {
			obsPlataformas = FXCollections.observableArrayList(plataformas);
			cbPlataforma.setItems(obsPlataformas);
		}

		Callback<ListView<JogoModel>, ListCell<JogoModel>> facJogo = lv -> new ListCell<JogoModel>() {
			@Override
			protected void updateItem(JogoModel item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		cbJogo.setCellFactory(facJogo);
		cbJogo.setButtonCell(facJogo.call(null));
		
		Callback<ListView<PlataformaModel>, ListCell<PlataformaModel>> facPlat = lv -> new ListCell<PlataformaModel>() {
			@Override
			protected void updateItem(PlataformaModel item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		cbPlataforma.setCellFactory(facPlat);
		cbPlataforma.setButtonCell(facPlat.call(null));
		
		cbJogo.getSelectionModel().selectFirst();
		cbPlataforma.getSelectionModel().selectFirst();
		
		jgSelect = (JogoModel) cbJogo.getSelectionModel().getSelectedItem();
		pfSelect = (PlataformaModel) cbPlataforma.getSelectionModel().getSelectedItem();
		
		Constraints.setTextFieldDouble(txtPreco);
		Constraints.setTextFieldInteger(txtQtd);
		onComboBoxChange();
	}

}
