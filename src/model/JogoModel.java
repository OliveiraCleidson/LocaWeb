package model;

public class JogoModel {
	private int id;
	private String nome;
	public JogoModel(int id, String nome) {
		super();
		this.id = id;
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
