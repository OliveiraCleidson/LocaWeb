package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bda.DataB;
import model.CarrinhoModel;

public class CarrinhoJDBC implements CarrinhoDAO {
	public CarrinhoModel findByIdJP(int idJ, int idP) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM carrinho WHERE idJogo = '" + idJ + "' and idPlataforma = '" + idP + "'");
			if (!rs.first() || rs.next()) {
				return null;
			}
			rs.first();
			CarrinhoModel carrinho = new CarrinhoModel(rs.getInt("idPlataforma"), rs.getInt("idJogo"),
					rs.getInt("quantidade"), rs.getDouble("preco"));
			return carrinho;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataB.closeResultSet(rs);
			DataB.closeStatement(st);
			DataB.closeConnection();
		}

		return null;
	}


	public List<CarrinhoModel> findByAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM carrinho");
			rs.beforeFirst();
			if (!rs.next())
				return null;
			rs.beforeFirst();
			List<CarrinhoModel> list = new ArrayList<CarrinhoModel>();
			while (rs.next()) {
				CarrinhoModel carrinho =  new CarrinhoModel(rs.getInt("idPlataforma"), rs.getInt("idJogo"),rs.getInt("quantidade"), rs.getDouble("preco"));
				list.add(carrinho);
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

	public boolean update(CarrinhoModel carrinho) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("UPDATE carrinho SET quantidade = '" + carrinho.getQuantidade() + "', preco = '" + carrinho.getPreco() 
					+ " WHERE idJogo = '" + carrinho.getIdJogo() + "' and idPlataforma = '" + carrinho.getIdPlataforma() + "'");
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
	public boolean insert(CarrinhoModel carrinho) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("INSERT INTO carrinho(idJogo,idPlataforma,quantidade,preco) VALUES ('" + carrinho.getIdJogo()
					+ "','" + carrinho.getIdPlataforma() + "','" + carrinho.getQuantidade() + "','" + carrinho.getPreco() + "')");
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
