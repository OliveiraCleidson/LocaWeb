package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bda.DataB;
import model.PlataformaModel;

public class PlataformaJDBC implements PlataformaDAO{
	@Override
	public PlataformaModel findById(int id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM plataforma WHERE id = " + id);
			if (!rs.first() || rs.next()) {
				return null;
			}
			rs.first();
			PlataformaModel plat = new PlataformaModel(rs.getInt("id"), rs.getString("nome"));
			return plat;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataB.closeResultSet(rs);
			DataB.closeStatement(st);
			DataB.closeConnection();
		}

		return null;
	}
	

	@Override
	public List<PlataformaModel> findByNome(String nome) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM plataforma WHERE nome = " + nome);
			rs.first();
			if(!rs.next())
				return null;
			rs.beforeFirst();
			List<PlataformaModel> list = new ArrayList<PlataformaModel>();
			while(rs.next()) {
				PlataformaModel plat = new PlataformaModel(rs.getInt("id"), rs.getString("nome"));
				list.add(plat);
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
	public List<PlataformaModel> findByAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM plataforma");
			if(!rs.next())
				return null;
			rs.beforeFirst();
			List<PlataformaModel> list = new ArrayList<PlataformaModel>();
			while(rs.next()) {
				PlataformaModel plat = new PlataformaModel(rs.getInt("id"), rs.getString("nome"));
				list.add(plat);
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
	public boolean update(PlataformaModel plat) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("UPDATE plat SET nome = '" 
			+ plat.getNome() 
			+ "' WHERE id = " + plat.getId());
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
	public boolean insert(PlataformaModel plat) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("INSERT INTO plataforma(nome) VALUES ('" 
			+ plat.getNome()
			+ "')");
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
	
}
