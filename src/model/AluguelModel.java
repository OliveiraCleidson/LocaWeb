package model;

import java.util.Date;

import model.dao.AluguelDAO;
import model.dao.jdbc.AluguelJDBC;

public class AluguelModel {
	private int id;
	private int idCliente;
	Date dataInicio;
	Date dataFim;
	private int ativo;
	private CarrinhoModel carrinho;
	private static AluguelDAO aluguelDAO = new AluguelJDBC();
	
	/**
	 * @return the carrinho
	 */
	public CarrinhoModel getCarrinho() {
		return carrinho;
	}

	public static AluguelDAO getAluguelDAO() {
		return aluguelDAO;
	}
	
	public AluguelModel(int idCliente, Date dataInicio, int ativo) {
		super();
		this.idCliente = idCliente;
		this.dataInicio = dataInicio;
		this.ativo = ativo;
	}
	
	
	
	public AluguelModel(int idCliente, Date dataInicio, Date dataFim, int ativo) {
		super();
		this.dataFim = dataFim;
		this.idCliente = idCliente;
		this.dataInicio = dataInicio;
		this.ativo = ativo;
	}
	
	public AluguelModel(int id, int idCliente, Date dataInicio, Date dataFim, int ativo) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.ativo = ativo;
	}
	
	public AluguelModel(int id, int idCliente, Date dataInicio, Date dataFim, int ativo, CarrinhoModel carrinho) {
		super();
		this.carrinho = carrinho;
		this.id = id;
		this.idCliente = idCliente;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.ativo = ativo;
	}
	
	
	
	@Override
	public String toString() {
		return "AluguelModel [id=" + id + ", idCliente=" + idCliente + ", dataInicio=" + dataInicio + ", dataFim="
				+ dataFim + ", ativo=" + ativo + ", carrinho=" + carrinho + "]";
	}

	//Getters and Setters
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	public int getId() {
		return id;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public Date getDataFim() {
		return dataFim;
	}
	
	
}
