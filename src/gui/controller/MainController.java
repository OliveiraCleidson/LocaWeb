package gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.AluguelModel;
import model.ClienteModel;
import model.EstoqueModel;
import model.JogoModel;
import model.PlataformaModel;

public class MainController implements Initializable {
	@FXML
	private MenuItem miCliente;
	@FXML
	private MenuItem miAluguel;
	@FXML
	private MenuItem miJogo;
	@FXML
	private MenuItem miPlataforma;
	@FXML
	private MenuItem miEstoque;
	@FXML
	private TableView<ClienteModel> tvCliente;
	@FXML
	private TableView<JogoModel> tvJogo;
	@FXML
	private TableView<PlataformaModel> tvPlat;
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
	private TableColumn<ClienteModel,String> tcUpdate;
	@FXML
	private TableColumn<ClienteModel,String> tcEmail;
	@FXML
	private TableColumn<JogoModel,Integer> tcIdJogo;
	@FXML
	private TableColumn<JogoModel,String> tcJogo;
	@FXML
	private TableColumn<PlataformaModel,Integer> tcIdPlat;
	@FXML
	private TableColumn<PlataformaModel,String> tcPlat;
	@FXML
    private ListView<ClienteModel> lstCliente;
    @FXML
    private ListView<AluguelModel> lstAluga;
    @FXML
    private Text txtDevo;
    @FXML
    private Text txtLoca;
    @FXML
    private Text txtAtivo;
    @FXML
    private Text txtPreTotal;
    @FXML
    private Text txtPreDia;
    @FXML
    private Button btnPagar;
    @FXML
    private AnchorPane anchPan;
	
	private static List<ClienteModel> clienteTb = ClienteModel.getClienteDao().findByAll();
	private static List<JogoModel> jogoTb = JogoModel.getJogoDAO().findByAll();
	private static List<PlataformaModel> platTb = PlataformaModel.getPlataformaDAO().findByAll();
	private static List<AluguelModel> aluguelTb;
	
	public static MainController prop;
	
	public static void atualizaClienteTb() {
		clienteTb = ClienteModel.getClienteDao().findByAll();
		jogoTb = JogoModel.getJogoDAO().findByAll();
		platTb = PlataformaModel.getPlataformaDAO().findByAll();
	}
	
	@FXML
	private void onClickPagar() {
		if(lstCliente.getSelectionModel().getSelectedItem() != null) {
			if(lstAluga.getSelectionModel().getSelectedItem() != null) {
				AluguelModel aluga = lstAluga.getSelectionModel().getSelectedItem();
				if(aluga.getAtivo() == 1) {
					EstoqueModel estoque = EstoqueModel.getEstoqueDAO().findByIdJP(aluga.getCarrinho().getIdJogo(), aluga.getCarrinho().getIdPlataforma());
					if(estoque != null) {
						estoque.adicionar();
						try {
							EstoqueModel.getEstoqueDAO().update(estoque);
						} catch (SQLException e) {
							Alert info = new Alert(Alert.AlertType.WARNING);
							info.setTitle("Gerenciador de Estoque");
							info.setHeaderText("Erro na Atualizacao");
							info.setContentText(e.getMessage());
							info.showAndWait();
						}
						aluga.setAtivo(0);
						AluguelModel.getAluguelDAO().update(aluga);
						Alert info = new Alert(Alert.AlertType.INFORMATION);
						info.setTitle("Gerenciador de Alugueis");
						info.setHeaderText("Aluguel Pago");
						info.setContentText("O Aluguel foi Pago!");
						info.showAndWait();
						atualizaAluguel();
					}
				}
			}
		} else {
			Alert info = new Alert(Alert.AlertType.WARNING);
			info.setTitle("Gerenciador de Alugueis");
			info.setHeaderText("ERROR");
			info.setContentText("O Aluguel nao esta mais ativo.");
			info.showAndWait();
		}
	}
		
	private ObservableList<ClienteModel> obsCliente;
	private ObservableList<JogoModel> obsJogo;
	private ObservableList<PlataformaModel> obsPlat;
	private ObservableList<AluguelModel> obsAluguel;
	
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
	
	public void miAluguelClick() throws IOException {
		Parent aluguelScreen = FXMLLoader.load(getClass().getResource("/gui/AluguelView.fxml"));
		Scene scAluguel = new Scene(aluguelScreen);
		Stage stAluguel = new Stage();
		staMain = stAluguel;
		stAluguel.setScene(scAluguel);
		stAluguel.setTitle("Gerenciador de Aluguel");
		stAluguel.initModality(Modality.WINDOW_MODAL);
		stAluguel.initOwner(Main.getWindow());
		stAluguel.show();
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
		lstCliente.setItems(obsCliente);
		obsJogo = FXCollections.observableArrayList(jogoTb);
		tvJogo.setItems(obsJogo);
		obsPlat = FXCollections.observableArrayList(platTb);
		tvPlat.setItems(obsPlat);
	}
	
	private void atualizaAluguel() {
		if(lstCliente.getSelectionModel().getSelectedItem() == null)
			lstCliente.getSelectionModel().selectFirst();
		int id = Integer.parseInt(lstCliente.getSelectionModel().getSelectedItem().getId());
		aluguelTb = AluguelModel.getAluguelDAO().findById(id);
		if(aluguelTb != null && !aluguelTb.isEmpty()) {
			obsAluguel = FXCollections.observableArrayList(aluguelTb);
			lstAluga.setItems(obsAluguel);
			Callback<ListView<AluguelModel>, ListCell<AluguelModel>> facAluga = lv -> new ListCell<AluguelModel>() {
				@Override
				protected void updateItem(AluguelModel item, boolean empty) {
					super.updateItem(item, empty);
					
					try {
						String oText;
						if(item != null)
							oText = item.getId() + ". " + JogoModel.getJogoDAO().findById(item.getCarrinho().getIdJogo()).getNome() + "/" + PlataformaModel.getPlataformaDAO().findById(item.getCarrinho().getIdPlataforma()).getNome();
						else
							oText = "null";
						setText(empty ? "" : oText);
						if(!empty) {
							if(item.getAtivo() != 1)
								setTextFill(Paint.valueOf(Color.DARKGREEN.toString()));
							else {
								Date dtDevo = item.getDataFim();
								Date dtLocal = new Date();
								if(dtLocal.compareTo(dtDevo) > 0)
									setTextFill(Paint.valueOf(Color.DARKRED.toString()));
								else
									setTextFill(Paint.valueOf(Color.DARKGOLDENROD.toString()));
							}
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						Alert info = new Alert(Alert.AlertType.WARNING);
						info.setTitle("Gerenciador de ERROS");
						info.setHeaderText("ERRROR RRRO ERROR");
						info.setContentText(e.getMessage());
						info.showAndWait();
					}
				}
			};
			lstAluga.setCellFactory(facAluga);
			lstAluga.getSelectionModel().selectLast();
			lstAluga.getSelectionModel().selectedItemProperty().addListener(v -> preencheAluguel());
			lstAluga.getSelectionModel().selectFirst();
		} else {
			lstAluga.getItems().clear();
			lstAluga.getSelectionModel().getSelectedItem();
		}
		
		
	}

	public void preencheAluguel() {
		if(lstAluga.getSelectionModel().getSelectedItem() != null) {
			AluguelModel aluguel = lstAluga.getSelectionModel().getSelectedItem();
			Date dtDevo = aluguel.getDataFim();
			Date dtLocal = aluguel.getDataInicio();
			SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
			txtDevo.setText(smp.format(dtDevo));
			txtLoca.setText(smp.format(dtLocal));
			String txtAtt;
			if(aluguel.getAtivo() == 1)
				txtAtt = "Sim";
			else
				txtAtt = "Ja foi pago!";
			txtAtivo.setText(txtAtt);
			txtPreDia.setText(String.format("%.2f", aluguel.getCarrinho().getPreco()));
			int diffInDays = (int)( (dtDevo.getTime() - dtLocal.getTime()) 
	                 / (1000 * 60 * 60 * 24) );
			Date hoje = new Date();
			int intTaxa = 0;
			if(hoje.compareTo(dtDevo) > 0) {
				int diffInDayss = (int)( (hoje.getTime() - dtDevo.getTime()) 
		                 / (1000 * 60 * 60 * 24) );
				intTaxa = diffInDayss*5;
			}
			txtPreTotal.setText(String.format("%.2f", diffInDays*aluguel.getCarrinho().getPreco()+intTaxa));
		} else {
			txtDevo.setText("");
			txtLoca.setText("");
			txtAtivo.setText("");
			txtPreDia.setText("");
			txtPreTotal.setText("");
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializaNodes();
		btnPagar.setOnAction(v->onClickPagar());
	}
	
	public void initializaNodes() {
		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
		tcIdJogo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcIdPlat.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcJogo.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcPlat.setCellValueFactory(new PropertyValueFactory<>("nome"));
		editableColums();
		updateTableView();
		prop = this;
		
		lstCliente.setItems(obsCliente);
		Callback<ListView<ClienteModel>, ListCell<ClienteModel>> facCliente = lv -> new ListCell<ClienteModel>() {
			@Override
			protected void updateItem(ClienteModel item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		lstCliente.setCellFactory(facCliente);
		lstCliente.getSelectionModel().selectLast();
		lstCliente.getSelectionModel().selectedItemProperty().addListener( v-> {
			atualizaAluguel();
		});
		lstCliente.getSelectionModel().selectFirst();
	}
	
	
	private void editableColums() {
		tcNome.setCellFactory(TextFieldTableCell.forTableColumn());
		tcNome.setOnEditCommit(e->{
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setNome(e.getNewValue());
			ClienteModel clienteEdit = e.getTableView().getItems().get(e.getTablePosition().getRow());
			ClienteModel.getClienteDao().update(clienteEdit);
		});
		tcEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		tcEmail.setOnEditCommit(e->{
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
			ClienteModel clienteEdit = e.getTableView().getItems().get(e.getTablePosition().getRow());
			ClienteModel.getClienteDao().update(clienteEdit);
		});
		tcTelefone.setCellFactory(TextFieldTableCell.forTableColumn());
		tcTelefone.setOnEditCommit(e->{
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setTelefone(e.getNewValue());
			ClienteModel clienteEdit = e.getTableView().getItems().get(e.getTablePosition().getRow());
			ClienteModel.getClienteDao().update(clienteEdit);
		});
		tvCliente.setEditable(true);
		
		tcJogo.setCellFactory(TextFieldTableCell.forTableColumn());
		tcJogo.setOnEditCommit(e->{
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setNome(e.getNewValue());
			JogoModel clienteEdit = e.getTableView().getItems().get(e.getTablePosition().getRow());
			JogoModel.getJogoDAO().update(clienteEdit);
		});
		
		tcPlat.setCellFactory(TextFieldTableCell.forTableColumn());
		tcPlat.setOnEditCommit(e->{
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setNome(e.getNewValue());
			PlataformaModel clienteEdit = e.getTableView().getItems().get(e.getTablePosition().getRow());
			PlataformaModel.getPlataformaDAO().update(clienteEdit);
		});
		
		tvJogo.setEditable(true);
		tvPlat.setEditable(true);
	}
}
