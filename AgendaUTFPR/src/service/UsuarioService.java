package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.UsuarioDAO;
import entities.Usuario;

public class UsuarioService {
	
	public UsuarioService() {
		
	}
	
	public List<Usuario> buscarUsuarios() throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new UsuarioDAO(conn).buscarUsuarios();
	}
	
	public void cadastrar(Usuario usuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new UsuarioDAO(conn).cadastrarUsuario(usuario);
	}
	
public boolean validarSenhaUsuario(String usuario, String senha) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return (new UsuarioDAO(conn).validarSenhaUsuario(usuario,senha));
	}
public boolean validarNomeUsuario(String usuario) throws SQLException, IOException {
	
	Connection conn = BancoDados.conectar();
	return (new UsuarioDAO(conn).validarNomeUsuario(usuario));
}

public void atualizarUsuario(Usuario usuario, String nomeUsuario) throws SQLException, IOException {
		
	Connection conn = BancoDados.conectar();
	new UsuarioDAO(conn).atualizarUsuario(usuario, nomeUsuario);
}

}
