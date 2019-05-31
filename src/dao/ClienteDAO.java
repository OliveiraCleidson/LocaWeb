package dao;

import java.sql.SQLException;
import java.util.List;

import model.ClienteModel;

public interface ClienteDAO {
	ClienteModel findById(int id);
	List<ClienteModel> findByNome(String nome);
	List<ClienteModel> findByAll();
	boolean update(ClienteModel cliente);	
	boolean insert(ClienteModel cliente) throws SQLException;
}
