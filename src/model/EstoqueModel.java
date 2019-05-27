package model;

public class EstoqueModel {
	private int idJogo;
	private int idPlataforma;
	private int quantidade;
	private double preco;
	
	public EstoqueModel(int idJogo, int idPlataforma, int quantidade, double preco) {
		super();
		this.idJogo = idJogo;
		this.idPlataforma = idPlataforma;
		this.quantidade = quantidade;
		this.preco = preco;
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
