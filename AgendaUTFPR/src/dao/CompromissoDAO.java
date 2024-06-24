package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.Agenda;
import entities.Compromisso;

public class CompromissoDAO {
	private Connection conn;
	private String descricaoAgenda;
	private int id_agenda;
	
	public CompromissoDAO(Connection conn) {
		this.conn = conn;
	}
	
	public String buscarDescricaoAgenda(String nomeAgenda, int idUsuario) throws SQLException{
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT descricao FROM agenda WHERE ? = id_usuario AND nome = ?");
			st.setInt(1, idUsuario);
			st.setString(2, nomeAgenda);
			
			rs = st.executeQuery();
			rs.next();
			
				descricaoAgenda = rs.getString("descricao");
				return descricaoAgenda;

		}
		finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public int buscarIdAgenda(String nomeAgenda) throws SQLException{
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id FROM agenda WHERE ? = nome");
			st.setString(1, nomeAgenda);
			rs = st.executeQuery();
			rs.next();
			
				id_agenda = rs.getInt("id");
				System.out.println("metodo buscar id agendas "+id_agenda);
				return id_agenda;

		}
		finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	public List<Compromisso> buscarCompromissos(int idAgenda) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

		//	st = conn.prepareStatement("select * from agenda order by nome");
			System.out.println("metodo buscar compromisso: "+idAgenda);
			st = conn.prepareStatement("select * from compromisso where ? = id_agenda");
			st.setInt(1, idAgenda);
			rs = st.executeQuery();

			List<Compromisso> listaCompromissos = new ArrayList<>();

			while (rs.next()) {

				Compromisso compromisso = new Compromisso();
				compromisso.setTitulo(rs.getString("titulo"));
				compromisso.setDescricao(rs.getString("descricao"));
				compromisso.setDataInicio(rs.getDate("dataHoraInicio"));
				compromisso.setHoraInicio(rs.getTime("dataHoraInicio"));
				compromisso.setHoraFim(rs.getTime("dataHoraFim"));
				compromisso.setDataFim(rs.getDate("dataHoraFim"));
				compromisso.setLocal(rs.getString("local"));

				listaCompromissos.add(compromisso);
			}

			return listaCompromissos;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}
}
