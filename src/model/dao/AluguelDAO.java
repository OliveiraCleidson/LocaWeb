package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.AluguelModel;

public interface AluguelDAO {
	List<AluguelModel> findById(int id);
	List<AluguelModel> findByAll();
	boolean update(AluguelModel aluguel);	
	boolean insert(AluguelModel aluguel) throws SQLException;
}
