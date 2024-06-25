package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.ImagemDAO;
import entities.Imagem;

public class ImagemService {
	
	public ImagemService() {
		// TODO Auto-generated constructor stub
	}
	
	public void nomeImagem(Imagem imagem, int idUsuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new ImagemDAO(conn).nomeImagem(imagem, idUsuario);
	}
}
