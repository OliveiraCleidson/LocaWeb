package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.CarrinhoModel;

public interface CarrinhoDAO {
	CarrinhoModel findByIdAluguel(int idAluguel,Connection conn);
	List<CarrinhoModel> findByAll();
	boolean update(CarrinhoModel carrinho);	
	boolean insert(CarrinhoModel carrinho) throws SQLException;
}
