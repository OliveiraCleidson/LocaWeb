package model;

import model.dao.CarrinhoDAO;
import model.dao.jdbc.CarrinhoJDBC;

public class CarrinhoModel extends EstoqueModel {
	private int idAluguel;
	private static final CarrinhoDAO carrinhoDao = new CarrinhoJDBC();
	
	public static CarrinhoDAO getCarrinhoDAO() {
		return carrinhoDao;
	}

	/**
	 * @return the idAluguel
	 */
	public int getIdAluguel() {
		return idAluguel;
	}

	/**
	 * @param idAluguel the idAluguel to set
	 */
	public void setIdAluguel(int idAluguel) {
		this.idAluguel = idAluguel;
	}

	
	public CarrinhoModel(int idAluguel, int idJogo, int idPlataforma, int quantidade, double preco) {
		super(idJogo, idPlataforma, quantidade, preco);
		this.idAluguel = idAluguel;
	}
	
	public CarrinhoModel(int idJogo, int idPlataforma, int quantidade, double preco) {
		super(idJogo, idPlataforma, quantidade, preco);
	}
	
}
