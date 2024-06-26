package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AgendaDAO;
import dao.BancoDados;
import dao.CompromissoDAO;
import entities.Agenda;
import entities.Compromisso;

public class CompromissoService {
	
	public String buscarDescricaoAgenda(String nomeAgenda, int idUsuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new CompromissoDAO(conn).buscarDescricaoAgenda(nomeAgenda, idUsuario);
		
	}
	
	public List<Compromisso> buscarCompromissos(int idAgenda) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new CompromissoDAO(conn).buscarCompromissos(idAgenda);
	}
	
	public int buscarIdAgenda(String nomeAgenda) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new CompromissoDAO(conn).buscarIdAgenda(nomeAgenda);
		
	}
	
	public void cadastrar(Compromisso compromisso, int idAgenda) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new CompromissoDAO(conn).cadastrarCompromisso(compromisso, idAgenda);
	}
	public void excluirCompromisso(Compromisso compromisso, String nomeCompromisso, int idAgenda) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new CompromissoDAO(conn).excluirCompromisso(compromisso, nomeCompromisso, idAgenda);
	}
	
}
