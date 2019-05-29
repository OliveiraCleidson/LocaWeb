package model;

import java.util.Date;

public class AluguelModel {
	private int id;
	private int idCliente;
	Date dataInicio;
	Date dataFim;
	int ativo;
	
	public AluguelModel(int idCliente, Date dataInicio, Date dataFim, int ativo) {
		super();
		this.idCliente = idCliente;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
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
	
	
	@Override
	public String toString() {
		return "AluguelModel [id=" + id + ", idCliente=" + idCliente + ", dataInicio=" + dataInicio + ", dataFim="
				+ dataFim + ", ativo=" + ativo + "]";
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
