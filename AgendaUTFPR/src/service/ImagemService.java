package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.AgendaDAO;
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
	
	public int buscarId(String usuario) throws SQLException, IOException {
			
		System.out.println(usuario);
		Connection conn = BancoDados.conectar();
		return new ImagemDAO(conn).buscarId(usuario);
		
	}
	public String buscarFoto(int Idusuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new ImagemDAO(conn).buscarFoto(Idusuario);
		
	}
}
