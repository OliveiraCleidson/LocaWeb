package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bda.DataB;
import model.ClienteModel;

public class ClienteJDBC implements ClienteDAO{

	public ClienteModel findById(int id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM cliente WHERE id = " + id);
			if(!rs.first() || rs.next()) {
				return null;
			}
			rs.first();
			ClienteModel cliente = new ClienteModel(rs.getString("id"), rs.getString("nome"), rs.getString("email"), rs.getString("rg"), rs.getString("cpf"), rs.getString("telefone"));
			return cliente;
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

	public List<ClienteModel> findByNome(String nome) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM cliente WHERE nome = " + nome);
			rs.first();
			if(!rs.next())
				return null;
			rs.beforeFirst();
			List<ClienteModel> list = new ArrayList<ClienteModel>();
			while(rs.next()) {
				ClienteModel cliente = new ClienteModel(rs.getString("id"), rs.getString("nome"), rs.getString("email"), rs.getString("rg"), rs.getString("cpf"), rs.getString("telefone"));
				list.add(cliente);
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
	
	public List<ClienteModel> findByAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM cliente");
			rs.beforeFirst();
			if(!rs.next())
				return null;
			rs.beforeFirst();
			List<ClienteModel> list = new ArrayList<ClienteModel>();
			while(rs.next()) {
				ClienteModel cliente = new ClienteModel(rs.getString("id"), rs.getString("nome"), rs.getString("email"), rs.getString("rg"), rs.getString("cpf"), rs.getString("telefone"));
				list.add(cliente);
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
	
	public boolean update(ClienteModel cliente) {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("UPDATE cliente SET nome = '" 
			+ cliente.getNome() 
			+ "', rg = " 
			+ cliente.getRg() 
			+ ", cpf = " 
			+ cliente.getCpf() 
			+ ", email = '" 
			+ cliente.getEmail() 
			+ "', telefone = " 
			+ cliente.getTelefone() 
			+ " WHERE id = " + cliente.getId());
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
	public boolean insert(ClienteModel cliente) throws SQLException {
		Connection conn = null;
		Statement st = null;
		int rs;
		try {
			conn = DataB.getConnection();
			st = conn.createStatement();
			rs = st.executeUpdate("INSERT INTO cliente(nome,rg,cpf,email,telefone) VALUES ('" 
			+ cliente.getNome()
			+ "','"
			+ cliente.getRg() 
			+ "','"
			+ cliente.getCpf() 
			+ "','"
			+ cliente.getEmail() 
			+ "','"
			+ cliente.getTelefone() 
			+ "')");
			if(rs == 1)
				return true;
			else
				return false;
		}
		finally {
			DataB.closeStatement(st);
			DataB.closeConnection();		
		}
	}

}
