package dao;

import java.util.List;

import model.AluguelModel;

public interface AluguelDAO {
	AluguelModel findById(int id);
	List<AluguelModel> findByAll();
	boolean update(AluguelModel aluguel);	
	boolean insert(AluguelModel aluguel);
}
