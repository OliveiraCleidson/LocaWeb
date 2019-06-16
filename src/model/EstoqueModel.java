package model;

import model.dao.EstoqueDAO;
import model.dao.EstoqueJDBC;

public class EstoqueModel {
	private int id;
	private int idJogo;
	private int idPlataforma;
	private int quantidade;
	private double preco;
	private static EstoqueDAO estoqueDao = new EstoqueJDBC();
	
	public static EstoqueDAO getEstoqueDAO() {
		return estoqueDao;
	}
	
	public static boolean insertEstoqueDB(int idJogo, int idPlataforma, int quantidade, double preco) {
		EstoqueModel estoque = new EstoqueModel(idJogo,idPlataforma,quantidade,preco);
		return estoqueDao.insert(estoque);
	}
	
	public static boolean insertEstoqueDB(EstoqueModel estoque) {
		return estoqueDao.insert(estoque);
	}
	
	public EstoqueModel(int idJogo, int idPlataforma, int quantidade, double preco) {
		super();
		this.idJogo = idJogo;
		this.idPlataforma = idPlataforma;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "EstoqueModel [idJogo=" + idJogo + ", idPlataforma=" + idPlataforma + ", quantidade=" + quantidade
				+ ", preco=" + preco + "]";
	}

	public boolean adicionar(int qtd) {
		this.quantidade = qtd;
		if(this.quantidade < this.quantidade + qtd)
			return false;
		else
			return true;
	}
	
	public boolean remover(int qtd) {
		this.quantidade = qtd;
		if(this.quantidade > this.quantidade - qtd)
			return false;
		else
			return true;
	}
	
	//Getters ands Setters
	public int getQuantidade() {
		return quantidade;
	}
	
	public int getIdJogo() {
		return idJogo;
	}
	public int getIdPlataforma() {
		return idPlataforma;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double price) {
		this.preco = price;
	}
	
}
