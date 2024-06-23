package entities;

public class Agenda {
	private String nome;
	private String descricao;
	
	private Compromisso[] compromisso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
