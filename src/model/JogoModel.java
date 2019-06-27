package model;

import java.sql.SQLException;
import java.util.List;

import model.dao.JogoDAO;
import model.dao.PlataformaDAO;
import model.dao.jdbc.JogoJDBC;

public class JogoModel {
	private int id;
	private String nome;
	private static JogoDAO jogoDao = new JogoJDBC();
	
	public static JogoDAO getJogoDAO() {
		return jogoDao;
	}
	
	public JogoModel(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public JogoModel(String nome) {
		super();
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "JogoModel [id=" + id + ", nome=" + nome + "]";
	}

	public static boolean insertJogoBD(String nome) throws SQLException {
		JogoModel jogo = new JogoModel(nome);
		jogoDao.insert(jogo);		
		jogo = jogoDao.findByNome(jogo.getNome()).get(0);
		PlataformaDAO plataformaDao = PlataformaModel.getPlataformaDAO();
		List<PlataformaModel> plataforma = plataformaDao.findByAll();
		if(plataforma != null) {
			for(PlataformaModel plat : plataforma) {
				if(EstoqueModel.getEstoqueDAO().findByIdJP(jogo.getId(), plat.getId()) == null) {
					EstoqueModel estoque = new EstoqueModel(jogo.getId(), plat.getId(), 0, 0);
					EstoqueModel.insertEstoqueDB(estoque);
				}
			}
		}
		return true;
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
