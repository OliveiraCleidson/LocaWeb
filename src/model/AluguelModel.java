package model;

import java.util.Calendar;

public class AluguelModel {
	private int id;
	private int idCliente;
	Calendar dataInicio;
	Calendar dataFim;
	int ativo;
	
	//Getters and Setters
	public Calendar getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Calendar dataInicio) {
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
	public Calendar getDataFim() {
		return dataFim;
	}
	
	
}
