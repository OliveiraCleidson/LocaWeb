package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bda.DataB;
import model.AluguelModel;

public class AluguelJDBC implements AluguelDAO{
	/* SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	Date data = formato.parse("02/05/1998"); */
	public AluguelModel findById(int id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM aluguel WHERE id = '" + id + "'");
			if (!rs.first() || rs.next()) {
				return null;
			}
			rs.first();
			AluguelModel aluguel = new AluguelModel(rs.getInt("id"), rs.getInt("idCliente"), rs.getDate("dataInicio"), rs.getDate("dataFim"), rs.getInt("ativo"));
			return aluguel;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataB.closeResultSet(rs);
			DataB.closeStatement(st);
			DataB.closeConnection();
		}

		return null;
	}


	public List<AluguelModel> findByAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM aluguel");
			if (!rs.next())
				return null;
			rs.beforeFirst();
			List<AluguelModel> list = new ArrayList<AluguelModel>();
			while (rs.next()) {
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				AluguelModel aluguel =  new AluguelModel(rs.getInt("id"), rs.getInt("idCliente"), formato.parse(rs.getDate("dataInicio").toString()), formato.parse(rs.getDate("dataFim").toString()), rs.getInt("ativo"));
				list.add(aluguel);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DataB.closeResultSet(rs);
			DataB.closeStatement(st);
			DataB.closeConnection();
		}

		return null;
	}

	public boolean update(AluguelModel aluguel) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("UPDATE aluguel SET dataInicio = '" + new java.sql.Date(aluguel.getDataInicio().getTime()) + "', dataFim = '" + new java.sql.Date(aluguel.getDataFim().getTime()) 
					+ " WHERE id = '" + aluguel.getId() + "' and idCliente = '" + aluguel.getIdCliente() + "'");
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
	public boolean insert(AluguelModel aluguel) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("INSERT INTO aluguel(idCliente,dataInicio,dataFim,ativo) VALUES ('" + aluguel.getIdCliente() + "','" + new java.sql.Date(aluguel.getDataInicio().getTime()) + "','" + new java.sql.Date(aluguel.getDataFim().getTime()) + "', '" + aluguel.getAtivo()  + "')");
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
