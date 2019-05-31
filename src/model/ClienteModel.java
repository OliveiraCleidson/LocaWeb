package model;

import java.sql.SQLException;

import dao.ClienteDAO;
import dao.ClienteJDBC;

public class ClienteModel {
	private String id;
	private String nome;
	private String email;
	private String rg;
	private String cpf;
	private String telefone;
	private static ClienteDAO clienteDao = new ClienteJDBC();

	
	public static boolean insertClienteBD(String nome, String rg, String cpf, String email, String telefone) throws SQLException {
		ClienteModel cliente = new ClienteModel(nome,rg,cpf,email,telefone);
		return clienteDao.insert(cliente);
	}
	
	@Override
	public String toString() {
		return "ClienteModel [id=" + id + ", nome=" + nome + ", email=" + email + ", rg=" + rg + ", cpf=" + cpf
				+ ", telefone=" + telefone + "]";
	}

	public ClienteModel(String nome, String rg) {
		super();
		this.nome = nome;
		this.rg = rg;
	}
	
	public ClienteModel(String id, String nome, String email, String rg, String cpf, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	public ClienteModel(String nome, String rg, String cpf, String email, String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	//Get and Setters
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRg() {
		return rg;
	}
	public String getCpf() {
		return cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
