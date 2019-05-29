package dao;

import java.util.List;

import model.EstoqueModel;

public interface EstoqueDAO {
	EstoqueModel findByIdJP(int idJ, int idP);
	List<EstoqueModel> findByAll();
	boolean update(EstoqueModel estoque);	
	boolean insert(EstoqueModel estoque);
}
