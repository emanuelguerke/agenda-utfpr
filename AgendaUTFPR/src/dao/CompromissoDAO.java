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
				compromisso.setDataInicio(rs.getDate("dataInicio"));
				compromisso.setHoraInicio(rs.getString("HoraInicio"));
				compromisso.setHoraFim(rs.getString("HoraFim"));
				compromisso.setDataFim(rs.getDate("dataFim"));
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
	
	private static java.sql.Date converterData(java.util.Date date){
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	
	public void cadastrarCompromisso(Compromisso compromisso, int idAgenda) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT IGNORE INTO compromisso (titulo, descricao, id_agenda, dataInicio, dataFim, local, horaInicio, horaFim ) VALUES (?, ?, ?,?,?,?,?,?)");
			st.setString(1, compromisso.getTitulo());
			st.setString(2, compromisso.getDescricao());
			st.setInt(3, idAgenda);
            st.setDate(4, converterData(compromisso.getDataInicio()));
            st.setDate(5, converterData(compromisso.getDataFim()));
        	st.setString(6, compromisso.getLocal());
        	st.setString(7, compromisso.getHoraInicio());
            st.setString(8, compromisso.getHoraFim());
			st.executeUpdate();
			
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
}
