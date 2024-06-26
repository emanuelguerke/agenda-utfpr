package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class ConviteDAO {
	private Connection conn;
	
	public ConviteDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void convidar() throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
}
