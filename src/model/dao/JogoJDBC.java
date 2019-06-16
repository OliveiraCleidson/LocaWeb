package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bda.DataB;
import model.JogoModel;

public class JogoJDBC implements JogoDAO{

	@Override
	public JogoModel findById(int id) throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM jogo WHERE id = " + id);
			if (!rs.first() || rs.next()) {
				return null;
			}
			rs.first();
			JogoModel jogo = new JogoModel(rs.getInt("id"), rs.getString("nome"));
			return jogo;
		} finally {
			DataB.closeResultSet(rs);
			DataB.closeStatement(st);
			DataB.closeConnection();
		}
	}
	

	@Override
	public List<JogoModel> findByNome(String nome) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM jogo WHERE nome = '" + nome + "'");
			if(!rs.next())
				return null;
			rs.beforeFirst();
			List<JogoModel> list = new ArrayList<JogoModel>();
			while(rs.next()) {
				JogoModel jogo = new JogoModel(rs.getInt("id"), rs.getString("nome"));
				list.add(jogo);
			}
			return list;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DataB.closeResultSet(rs);
			DataB.closeStatement(st);
			DataB.closeConnection();		
		}
		
		return null;
	}

	@Override
	public List<JogoModel> findByAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM jogo ORDER BY nome");
			if(!rs.next())
				return null;
			rs.beforeFirst();
			List<JogoModel> list = new ArrayList<JogoModel>();
			while(rs.next()) {
				JogoModel jogo = new JogoModel(rs.getInt("id"), rs.getString("nome"));
				list.add(jogo);
			}
			return list;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DataB.closeResultSet(rs);
			DataB.closeStatement(st);
			DataB.closeConnection();		
		}
		
		return null;
	}

	@Override
	public boolean update(JogoModel jogo) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("UPDATE jogo SET nome = '" 
			+ jogo.getNome() 
			+ "' WHERE id = " + jogo.getId());
			if(rs == 1)
				return true;
			else
				return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DataB.closeStatement(st);
			DataB.closeConnection();		
		}
		
		return false;
	}


	@Override
	public boolean insert(JogoModel jogo) throws SQLException {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("INSERT INTO jogo(nome) VALUES ('" 
			+ jogo.getNome()
			+ "')");
			if(rs == 1)
				return true;
			else
				return false;
		}finally {
			DataB.closeStatement(st);
			DataB.closeConnection();		
		}
	}
	
}
