package gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.AluguelModel;
import model.CarrinhoModel;
import model.ClienteModel;
import model.EstoqueModel;
import model.JogoModel;
import model.PlataformaModel;

public class AluguelController implements Initializable{
	@FXML
	private ListView<ClienteModel> lsCliente;
	@FXML
	private ListView<JogoModel> lsJogo;
	@FXML
	private ListView<PlataformaModel> lsPlat;
	@FXML
	private Button btnAlugar;
	@FXML
	private Text txtQtd;
	@FXML
	private Text txtPreco;
	@FXML
	private DatePicker dtL;
	@FXML
	private DatePicker dtD;
	private EstoqueModel estoque;
	private List<EstoqueModel> estoques;
	private ObservableList<ClienteModel> obsCliente;
	private ObservableList<JogoModel> obsJogo;
	private ObservableList<PlataformaModel> obsPlat;
	private List<ClienteModel> clienteTb = ClienteModel.getClienteDao().findByAll();
	private List<JogoModel> jogoTb = JogoModel.getJogoDAO().findByAll();
	private List<PlataformaModel> platTb = PlataformaModel.getPlataformaDAO().findByAll();
	private EstoqueModel carrinho;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		obsCliente = FXCollections.observableArrayList(clienteTb);
		lsCliente.setItems(obsCliente);
		Callback<ListView<ClienteModel>, ListCell<ClienteModel>> facCliente = lv -> new ListCell<ClienteModel>() {
			@Override
			protected void updateItem(ClienteModel item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		lsCliente.setCellFactory(facCliente);
		obsJogo = FXCollections.observableArrayList(jogoTb);
		lsJogo.setItems(obsJogo);
		Callback<ListView<JogoModel>, ListCell<JogoModel>> facJogo = lv -> new ListCell<JogoModel>() {
			@Override
			protected void updateItem(JogoModel item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		lsJogo.setCellFactory(facJogo);
		obsPlat = FXCollections.observableArrayList(platTb);
		lsPlat.setItems(obsPlat);
		Callback<ListView<PlataformaModel>, ListCell<PlataformaModel>> facPlat = lv -> new ListCell<PlataformaModel>() {
			@Override
			protected void updateItem(PlataformaModel item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		estoques = EstoqueModel.getEstoqueDAO().findByAll();
		lsPlat.setCellFactory(facPlat);
		lsPlat.getSelectionModel().selectFirst();
		lsJogo.getSelectionModel().selectFirst();
		lsCliente.getSelectionModel().selectFirst();
		lsJogo.getSelectionModel().selectedItemProperty().addListener( (v) -> atualizaQtd() );
		lsPlat.getSelectionModel().selectedItemProperty().addListener( (v) -> atualizaQtd() );
		lsCliente.getSelectionModel().selectedItemProperty().addListener( (v) -> atualizaQtd() );
		dtL.setValue(LocalDate.now());
		dtD.setValue(LocalDate.now());
		atualizaQtd();
	}
	
	@FXML
	private void onClickData() {
		
		
	}
	
	private void atualizaQtd(){
		estoque = EstoqueModel.getEstoqueDAO().findByIdJP(lsJogo.getSelectionModel().getSelectedItem().getId(), lsPlat.getSelectionModel().getSelectedItem().getId());
		/*estoque = new EstoqueModel(lsJogo.getSelectionModel().getSelectedItem().getId(),lsPlat.getSelectionModel().getSelectedItem().getId(),-1,-1);
		for(EstoqueModel itens : estoques) {
			if(itens.getIdJogo() == estoque.getIdJogo() && itens.getIdPlataforma() == estoque.getIdPlataforma())
				estoque = itens;
		}*/
		txtQtd.setText(Integer.toString(estoque.getQuantidade()));
		txtPreco.setText("R$ " +  Double.toString(estoque.getPreco()));
	}
	

	private void btnSelect() {
		if(estoque.getQuantidade() > 0) {
			EstoqueModel item = new CarrinhoModel(lsJogo.getSelectionModel().getSelectedItem().getId(), lsPlat.getSelectionModel().getSelectedItem().getId(), estoque.getQuantidade(),estoque.getPreco());
			//Alugar
		} else {
			//Mostrar Aviso que nao eh possivel
		}
	}
	/**/
	@FXML
	private void btnClick() {
		if( estoque.getQuantidade() > 0 ) {
			try {
				String dataL = dtL.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				String dataD = dtD.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));	
				java.util.Date dataLoca = new SimpleDateFormat("dd/MM/yyyy").parse(dataL);
				java.util.Date dataDevo	=	new SimpleDateFormat("dd/MM/yyyy").parse(dataD);  
				if(dataLoca.compareTo(dataDevo) >= 0)
					throw new RuntimeException("A Data de Devolução deve ser apos a data delocação!");				
				AluguelModel aluguel = new AluguelModel(Integer.parseInt(lsCliente.getSelectionModel().getSelectedItem().getId()), dataLoca,dataDevo, 1);
				AluguelModel.getAluguelDAO().insert(aluguel);
				List<AluguelModel> list = AluguelModel.getAluguelDAO().findByAll();
				aluguel = list.get(list.size()-1);
				CarrinhoModel item = new CarrinhoModel(aluguel.getId(),estoque.getIdJogo(), estoque.getIdPlataforma(), estoque.getQuantidade(),estoque.getPreco());
				CarrinhoModel.getCarrinhoDAO().insert(item);
				estoque.remover();
				EstoqueModel.getEstoqueDAO().update(estoque);
				Alert info = new Alert(Alert.AlertType.INFORMATION);
				info.setTitle("Gerenciador de Alugueis");
				info.setHeaderText("Aluguel Efetivado");
				info.setContentText("O Jogo foi alugado!");
				info.showAndWait();
				atualizaQtd();
			} catch (SQLException | ParseException e) { 
				Alert info = new Alert(Alert.AlertType.WARNING);
				info.setTitle("Gerenciador de Alugueis");
				info.setHeaderText("Erro ! Parse or SQL");
				info.setContentText(e.getMessage());
				info.showAndWait();
			} catch (RuntimeException e) {
				Alert info = new Alert(Alert.AlertType.WARNING);
				info.setTitle("Gerenciador de Alugueis");
				info.setHeaderText("Erro ! ");
				info.setContentText(e.getMessage());
				info.showAndWait();
			}
		} else {
			Alert info = new Alert(Alert.AlertType.WARNING);
			info.setTitle("Gerenciador de Alugueis");
			info.setHeaderText("ESTOQUE ERROR");
			info.setContentText("Nao temos jogos no estoque para alugar!");
			info.showAndWait();
		}
	}
}
