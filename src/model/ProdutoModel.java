package model;

public class ProdutoModel {
	private int idJogo;
	private int idPlataforma;
	private int quantidade;
	private double preco;
	
	//Getters ands Setters
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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
	
}
