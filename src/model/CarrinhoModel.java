package model;

public class CarrinhoModel extends EstoqueModel {
	private int idAluguel;

	public CarrinhoModel(int idJogo, int idPlataforma, int quantidade, double preco) {
		super(idJogo, idPlataforma, quantidade, preco);
	}
	
	public CarrinhoModel(int idAluguel, int idJogo, int idPlataforma, int quantidade, double preco) {
		super(idJogo, idPlataforma, quantidade, preco);
		this.idAluguel = idAluguel;
	}
	
}
