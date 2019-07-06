package gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.JogoModel;

public class DeletaJogoController implements Initializable {
	@FXML
	private ComboBox<JogoModel> cbJogo;
	@FXML
	private Button btnDeletar;
	
	private ObservableList<JogoModel> obsJogos;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		List<JogoModel> jogos = JogoModel.getJogoDAO().findByAll();
		if (jogos != null) {
			obsJogos = FXCollections.observableArrayList(jogos);
			cbJogo.setItems(obsJogos);

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
		cbJogo.getSelectionModel().selectFirst();
		
	}
	@FXML
	private void onClickDel() {
		JogoModel jogoLoca = cbJogo.getSelectionModel().getSelectedItem();
	}
}
