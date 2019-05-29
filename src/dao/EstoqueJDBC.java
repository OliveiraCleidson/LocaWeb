package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bda.DataB;
import model.EstoqueModel;

public class EstoqueJDBC implements EstoqueDAO {
	public EstoqueModel findByIdJP(int idJ, int idP) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM estoque WHERE idJogo = '" + idJ + "' and idPlataforma = '" + idP + "'");
			if (!rs.first() || rs.next()) {
				return null;
			}
			rs.first();
			EstoqueModel estoque = new EstoqueModel(rs.getInt("idPlataforma"), rs.getInt("idJogo"),
					rs.getInt("quantidade"), rs.getDouble("preco"));
			return estoque;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataB.closeResultSet(rs);
			DataB.closeStatement(st);
			DataB.closeConnection();
		}

		return null;
	}


	public List<EstoqueModel> findByAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM estoque");
			rs.beforeFirst();
			if (!rs.next())
				return null;
			rs.beforeFirst();
			List<EstoqueModel> list = new ArrayList<EstoqueModel>();
			while (rs.next()) {
				EstoqueModel estoque =  new EstoqueModel(rs.getInt("idPlataforma"), rs.getInt("idJogo"),rs.getInt("quantidade"), rs.getDouble("preco"));
				list.add(estoque);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataB.closeResultSet(rs);
			DataB.closeStatement(st);
			DataB.closeConnection();
		}

		return null;
	}

	public boolean update(EstoqueModel estoque) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("UPDATE estoque SET quantidade = '" + estoque.getQuantidade() + "', preco = '" + estoque.getPreco() 
					+ " WHERE idJogo = '" + estoque.getIdJogo() + "' and idPlataforma = '" + estoque.getIdPlataforma() + "'");
			if (rs == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataB.closeStatement(st);
			DataB.closeConnection();
		}

		return false;
	}

	@Override
	public boolean insert(EstoqueModel estoque) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("INSERT INTO estoque(idJogo,idPlataforma,quantidade,preco) VALUES ('" + estoque.getIdJogo()
					+ "','" + estoque.getIdPlataforma() + "','" + estoque.getQuantidade() + "','" + estoque.getPreco() + "')");
			if (rs == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataB.closeStatement(st);
			DataB.closeConnection();
		}

		return false;
	}
}
