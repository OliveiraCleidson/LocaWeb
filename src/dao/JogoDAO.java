package dao;

import java.sql.SQLException;
import java.util.List;

import model.JogoModel;

public interface JogoDAO {
	JogoModel findById(int id) throws SQLException;
	List<JogoModel> findByNome(String nome);
	List<JogoModel> findByAll();
	boolean update(JogoModel jogo);	
	boolean insert(JogoModel jogo) throws SQLException;
}
