package entities;

public class Compromisso {
	private String titulo;
	private String descricao;
	private String dataHoraInicio;
	private String dataHoraFim;
	private String local;
	private String[] convidado;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDataHoraInicio() {
		return dataHoraInicio;
	}
	public void setDataHoraInicio(String dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	public String getDataHoraFim() {
		return dataHoraFim;
	}
	public void setDataHoraFim(String dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String[] getConvidado() {
		return convidado;
	}
	public void setConvidado(String[] convidado) {
		this.convidado = convidado;
	}
	
	
	
}
