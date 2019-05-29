package dao;

import java.util.List;

import model.PlataformaModel;

public interface PlataformaDAO {
	PlataformaModel findById(int id);
	List<PlataformaModel> findByNome(String nome);
	List<PlataformaModel> findByAll();
	boolean update(PlataformaModel plat);	
	boolean insert(PlataformaModel plat);
}
