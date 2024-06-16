package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.Usuario;

public class UsuarioDAO {
	
	private Connection conn;
	
	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Usuario> buscarUsuarios() throws SQLException {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT a.*" + "FROM usuario a");
			rs = st.executeQuery();
			
			List<Usuario> listaUsuarios = new ArrayList<>();
			
			while(rs.next()) {
				
				Usuario usuario = new Usuario();
				
				usuario.setNomeCompleto(rs.getString("nome_completo"));
				usuario.setDataNascimento(rs.getDate("data_nascimento"));
				usuario.setGenero(rs.getString("genero"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setSenha(rs.getString("senha"));
				
				listaUsuarios.add(usuario);
			}
			
			return listaUsuarios;
			
		} finally {
			
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
			
		}
		
	}
	
	public void cadastrarUsuario(Usuario usuario) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT IGNORE INTO usuario (nome_completo, data_nascimento, genero, email, nome_usuario, senha) VALUES (?, ?, ?, ?, ?, ?)");
			st.setString(1, usuario.getNomeCompleto());
			st.setDate(2, new Date(usuario.getDataNascimento().getTime()));
			st.setString(3, usuario.getGenero());
			st.setString(4, usuario.getEmail());
			st.setString(5, usuario.getNomeUsuario());
			st.setString(6, usuario.getSenha());
			st.executeUpdate();
			
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public boolean validarNomeUsuario(String usuario) throws SQLException{
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT nome_usuario FROM usuario WHERE ? = nome_usuario");
			st.setString(1, usuario);
			rs = st.executeQuery();
		//	if(rs.getString("nome_usuario") == usuario && rs.getString("senha") == senha ) {
			if(!rs.next()) {
				System.out.println("Usuario valido");
				return true;
			}else {
				System.out.println("Nome de usuario já cadastrado");
				return false;
			}
		}
		finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	
	public boolean validarSenhaUsuario(String usuario, String senha) throws SQLException{
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT nome_usuario, senha FROM usuario WHERE ? = nome_usuario and ? = senha ");
			st.setString(1, usuario);
			st.setString(2, senha);
			rs = st.executeQuery();
		//	if(rs.getString("nome_usuario") == usuario && rs.getString("senha") == senha ) {
			if(rs.next()) {
				System.out.println("senha correta");
				return true;
			}else {
				System.out.println("senha ou usuario incorreto");
				return false;
			}
		}
		finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
}
