package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import entities.Imagem;

public class ImagemDAO {
	
	private Connection conn;
	private int id_usuario;
	
	public ImagemDAO(Connection conn) {
		
		this.conn = conn;
	}
	
	public void nomeImagem(Imagem imagem, int idUsuario) throws SQLException {
		
		PreparedStatement st = null;
		String nome;
		
		try {
			
			st = conn.prepareStatement("INSERT IGNORE INTO imagem (nome, id_usuario) VALUES (?, ?)");
			st.setString(1, imagem.getNome());
			st.setInt(2, idUsuario);
			
			st.executeUpdate();
			
			
		} finally {
			
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();			
		}
	}
	
	public int buscarId(String usuario) throws SQLException{
		
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT id FROM usuario WHERE ? = nome_usuario");
				st.setString(1, usuario);
				rs = st.executeQuery();
				rs.next();
				//if(rs.getString("nome_usuario") == usuario && rs.getString("senha") == senha ) {
				
					id_usuario = rs.getInt("id");
					System.out.println(id_usuario);
					return id_usuario;

		} finally {
			
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}
	
}
