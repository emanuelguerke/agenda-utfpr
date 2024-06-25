package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import entities.Imagem;

public class ImagemDAO {
	
	private Connection conn;
	private int idUsuario;
	
	public ImagemDAO(Connection conn) {
		
		this.conn = conn;
	}
	
	public void nomeImagem(Imagem imagem, int idUsuario) throws SQLException {
		
		PreparedStatement st = null;
		
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
	
}
