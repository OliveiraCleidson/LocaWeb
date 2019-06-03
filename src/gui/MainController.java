package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ClienteModel;

public class MainController implements Initializable {
	@FXML
	private MenuItem miCliente;
	@FXML
	private MenuItem miJogo;
	@FXML
	private MenuItem miPlataforma;
	@FXML
	private MenuItem miEstoque;
	@FXML
	private TableView<ClienteModel> tvCliente;
	@FXML
	private TableColumn<ClienteModel,Integer> tcId;
	@FXML
	private TableColumn<ClienteModel,String> tcNome;
	@FXML
	private TableColumn<ClienteModel,String> tcCpf;
	@FXML
	private TableColumn<ClienteModel,String> tcRg;
	@FXML
	private TableColumn<ClienteModel,String> tcTelefone;
	@FXML
	private TableColumn<ClienteModel,String> tcEmail;
	
	private static List<ClienteModel> clienteTb = ClienteModel.getClienteDao().findByAll();
	
	public static void atualizaClienteTb() {
		clienteTb = ClienteModel.getClienteDao().findByAll();
	}
		
	private ObservableList<ClienteModel> obsCliente;
	
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
		updateTableView();
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
		updateTableView();
	}
	
	
	public static Stage getStg() {
		return staMain;
	}
	
	public void updateTableView() {
		atualizaClienteTb();
		obsCliente = FXCollections.observableArrayList(clienteTb);
		tvCliente.setItems(obsCliente);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializaNodes();
	}
	
	public void initializaNodes() {
		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
		updateTableView();
	}
	
	
}
