package model;

import model.dao.EstoqueDAO;
import model.dao.jdbc.EstoqueJDBC;

public class EstoqueModel {
	private int id;
	private int idJogo;
	private int idPlataforma;
	private int quantidade;
	private double preco;
	private static EstoqueDAO estoqueDao = new EstoqueJDBC();
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

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
	
	
	public EstoqueModel(int id,int idJogo, int idPlataforma, int quantidade, double preco) {
		super();
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idJogo;
		result = prime * result + idPlataforma;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstoqueModel other = (EstoqueModel) obj;
		if (idJogo != other.idJogo)
			return false;
		if (idPlataforma != other.idPlataforma)
			return false;
		return true;
	}

	public boolean adicionar(int qtd) {
		this.quantidade = qtd;
		if(this.quantidade < this.quantidade + qtd)
			return false;
		else
			return true;
	}
	
	public boolean adicionar() {
		++this.quantidade;
		return true;
	}
	
	public boolean remover() {
		this.quantidade--;
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

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
