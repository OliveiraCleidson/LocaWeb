package model.dao;

import java.util.List;

import model.CarrinhoModel;

public interface CarrinhoDAO {
	CarrinhoModel findByIdJP(int idJ, int idP);
	List<CarrinhoModel> findByAll();
	boolean update(CarrinhoModel carrinho);	
	boolean insert(CarrinhoModel carrinho);
}
