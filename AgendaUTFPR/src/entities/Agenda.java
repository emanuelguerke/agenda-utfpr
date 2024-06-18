package entities;

import java.util.Date;

public class Agenda {
	private int id;
	private String nome;
	private Date data;
	private String descricao;
	
	private Compromisso[] compromisso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Compromisso[] getCompromisso() {
		return compromisso;
	}

	public void setCompromisso(Compromisso[] compromisso) {
		this.compromisso = compromisso;
	}
	

}
