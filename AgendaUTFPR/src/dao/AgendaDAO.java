package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.Agenda;
import entities.Usuario;


public class AgendaDAO {
	private Connection conn;
	private int id_usuario;
	
	public AgendaDAO(Connection conn) {
		this.conn = conn;
	}
	
	public int buscarId(String usuario) throws SQLException{
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id FROM usuario WHERE ? = nome_usuario");
			st.setString(1, usuario);
			rs = st.executeQuery();
			rs.next();
		//	if(rs.getString("nome_usuario") == usuario && rs.getString("senha") == senha ) {
			
				id_usuario = rs.getInt("id");
				System.out.println(id_usuario);
				return id_usuario;

		}
		finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public void cadastrarAgenda(Agenda agenda, int idUsuario) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT IGNORE INTO agenda (nome, descricao, id_usuario) VALUES (?, ?, ?)");
			st.setString(1, agenda.getNome());
			st.setString(2, agenda.getDescricao());
		
			st.setInt(3, idUsuario);
			st.executeUpdate();
			
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public List<Agenda> buscarAgendas(int idUsuario) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

		//	st = conn.prepareStatement("select * from agenda order by nome");
			System.out.println(id_usuario);
			st = conn.prepareStatement("select * from agenda where ? = id_usuario");
			st.setInt(1, idUsuario);
			rs = st.executeQuery();

			List<Agenda> listaAgendas = new ArrayList<>();

			while (rs.next()) {

				Agenda agenda = new Agenda();
				agenda.setNome(rs.getString("nome"));
				agenda.setDescricao(rs.getString("descricao"));
				

				listaAgendas.add(agenda);
			}

			return listaAgendas;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}
	public void excluirAgenda(Agenda agenda, String nomeAgenda, int idUsuario) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM agenda WHERE nome = ? AND id_usuario = ?");
			
			st.setString(1, nomeAgenda);
			st.setInt(2, idUsuario);
			
			st.executeUpdate();
		
		} finally {
			
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
}
