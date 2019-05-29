package model;

public class PlataformaModel {
	private int id;
	private String nome;
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
