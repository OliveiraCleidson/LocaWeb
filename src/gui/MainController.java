package gui;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private MenuItem miCliente;
	
	@FXML
	private MenuItem miJogo;
	
	@FXML
	private MenuItem miPlataforma;
	
	@FXML
	private MenuItem miEstoque;
	
	private static Stage staMain;
	
	public void miEstoque() throws IOException {
		Parent estoqueScreen = FXMLLoader.load(getClass().getResource("/gui/EstoqueView.fxml"));
		Scene scEstoque = new Scene(estoqueScreen);
		Stage stEstoque = new Stage();
		staMain = stEstoque;
		stEstoque.setScene(scEstoque);
		stEstoque.setTitle("Gerenciador de Estoque");
		stEstoque.initModality(Modality.WINDOW_MODAL);
		stEstoque.initOwner(Main.getWindow());
		stEstoque.show();
	}
	
	
	public void miClienteonClick() throws IOException {
		Parent clienteScreen = FXMLLoader.load(getClass().getResource("/gui/ClienteView.fxml"));
		Scene scCliente = new Scene(clienteScreen);
		Stage stCliente = new Stage();
		staMain = stCliente;
		stCliente.setScene(scCliente);
		stCliente.setTitle("Gerenciador de Clientes");
		stCliente.initModality(Modality.WINDOW_MODAL);
		stCliente.initOwner(Main.getWindow());
		stCliente.show();
	}
	
	public void miJogoClick() throws IOException {
		Parent jogoScreen = FXMLLoader.load(getClass().getResource("/gui/JogoView.fxml"));
		Scene scJogo = new Scene(jogoScreen);
		Stage stJogo = new Stage();
		staMain = stJogo;
		stJogo.setScene(scJogo);
		stJogo.setTitle("Gerenciador de Jogos");
		stJogo.initModality(Modality.WINDOW_MODAL);
		stJogo.initOwner(Main.getWindow());
		stJogo.show();
	}
	
	public void miPlataformaClick() throws IOException {
		Parent plataformaScreen = FXMLLoader.load(getClass().getResource("/gui/PlataformaView.fxml"));
		Scene scPlataforma = new Scene(plataformaScreen);
		Stage stPlataforma = new Stage();
		staMain = stPlataforma;
		stPlataforma.setScene(scPlataforma);
		stPlataforma.setTitle("Gerenciador de Plataformas");
		stPlataforma.initModality(Modality.WINDOW_MODAL);
		stPlataforma.initOwner(Main.getWindow());
		stPlataforma.show();
	}
	
	
	public static Stage getStg() {
		return staMain;
	}
	
	
}
