package model;



public class ClienteModel {
	private int id;
	private String nome;
	private String email;
	private int rg;
	private int cpf;
	private int telefone;

	@Override
	public String toString() {
		return "ClienteModel [id=" + id + ", nome=" + nome + ", email=" + email + ", rg=" + rg + ", cpf=" + cpf
				+ ", telefone=" + telefone + "]";
	}

	public ClienteModel(String nome, int rg) {
		super();
		this.nome = nome;
		this.rg = rg;
	}
	
	public ClienteModel(int id, String nome, String email, int rg, int cpf, int telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	public ClienteModel(String nome, String email, int rg, int cpf, int telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	//Get and Setters
	public int getId() {
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
	public int getRg() {
		return rg;
	}
	public int getCpf() {
		return cpf;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
}
