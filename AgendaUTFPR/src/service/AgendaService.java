package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AgendaDAO;
import dao.BancoDados;
import dao.UsuarioDAO;
import entities.Agenda;
import entities.Usuario;

public class AgendaService {
	
	public void cadastrar(Agenda agenda, int idUsuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new AgendaDAO(conn).cadastrarAgenda(agenda, idUsuario);
	}
	
	public List<Agenda> buscarAgendas(int idUsuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new AgendaDAO(conn).buscarAgendas(idUsuario);
	}
	
	public int buscarId(String usuario) throws SQLException, IOException {
		System.out.println(usuario);
		Connection conn = BancoDados.conectar();
		return new AgendaDAO(conn).buscarId(usuario);
		
	}
	
}
