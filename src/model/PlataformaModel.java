package model;

import java.sql.SQLException;
import java.util.List;

import model.dao.JogoDAO;
import model.dao.PlataformaDAO;
import model.dao.PlataformaJDBC;

public class PlataformaModel {
	private int id;
	private String nome;
	private static PlataformaDAO plataformaDao = new PlataformaJDBC();
	
	public static boolean insertPlataformaBD(String nome) throws SQLException {
		PlataformaModel plataforma = new PlataformaModel(nome);
		plataformaDao.insert(plataforma);
		plataforma = plataformaDao.findByNome(plataforma.getNome()).get(0);
		JogoDAO jogoDao = JogoModel.getJogoDAO();
		List<JogoModel> jogo = jogoDao.findByAll();
		if(jogo != null) {
			for(JogoModel jg : jogo) {
				if(EstoqueModel.getEstoqueDAO().findByIdJP(jg.getId(), plataforma.getId()) == null) {
					EstoqueModel estoque = new EstoqueModel(jg.getId(), plataforma.getId(), 0, 0);
					EstoqueModel.insertEstoqueDB(estoque);
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "PlataformaModel [id=" + id + ", nome=" + nome + "]";
	}

	public static PlataformaDAO getPlataformaDAO() {
		return plataformaDao;
	}
	public PlataformaModel(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public PlataformaModel(String nome) {
		super();
		this.nome = nome;
	}

	//Getters and Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	
	
}
