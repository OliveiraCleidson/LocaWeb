package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.EstoqueModel;

public interface EstoqueDAO {
	EstoqueModel findByIdJP(int idJ, int idP);
	List<EstoqueModel> findByAll();
	boolean update(EstoqueModel estoque) throws SQLException;	
	boolean insert(EstoqueModel estoque);
}
