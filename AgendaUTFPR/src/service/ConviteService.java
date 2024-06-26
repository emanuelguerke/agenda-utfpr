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
import dao.ConviteDAO;

public class ConviteService {
	
	public void convidar() throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new ConviteDAO(conn).convidar();
	}
	
}
