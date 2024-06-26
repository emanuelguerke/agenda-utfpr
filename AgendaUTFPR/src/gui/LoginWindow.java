package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Usuario;
import service.UsuarioService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import entities.Imagem;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeUsuario;
	private JPasswordField fieldSenha;
	private UsuarioService usuarioService;
	private CadastroWindow usuarioWindow;
	private AgendaWindow agendaWindow;
	private Imagem imagem;

	public LoginWindow() {
		setTitle("Login");
		
		this.iniciarComponentes();
		this.usuarioService = new UsuarioService();
		
	}
	
	private void abrirCadastroUsuario() {
		
		CadastroWindow janelaUsuario = new CadastroWindow();
		janelaUsuario.setVisible(true);
		
		this.setVisible(false);
	}
	
	
	private void abrirAgenda(String nomeUsuario) {
		
		AgendaWindow agendaWindow = new AgendaWindow(nomeUsuario);
		agendaWindow.setVisible(true);
		
		this.setVisible(false);
		
	}
	private void abrirImagem(String nomeUsuario) {
		
		ImagemWindow imagemWindow = new ImagemWindow(nomeUsuario);
		imagemWindow.setVisible(true);
		
		this.setVisible(false);
		
	}
	
	public boolean validarCampos() {
		if(txtNomeUsuario.getText() != null && !txtNomeUsuario.getText().isEmpty() && fieldSenha.getText() != null && !fieldSenha.getText().isEmpty()) {
			return true;
		}else {
			return false;
		}
		
	}
	public boolean validarSenhaUsuario() {
		try {
			if(this.usuarioService.validarSenhaUsuario(txtNomeUsuario.getText(), fieldSenha.getText())) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException | IOException | NumberFormatException e) {
			System.out.println(e);
			return false;
		}
	}
	
	private void iniciarComponentes() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 408);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel painelBV = new JPanel();
		painelBV.setBackground(new Color(255, 255, 255));
		painelBV.setBounds(78, 27, 266, 202);
		contentPane.add(painelBV);
		painelBV.setLayout(null);
		painelBV.setBorder(BorderFactory.createTitledBorder(""));
		
		JLabel lblBemVindo = new JLabel("Bem-Vindo");
		lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBemVindo.setBounds(93, 11, 86, 14);
		painelBV.add(lblBemVindo);
		
		JLabel lblNomeUsuario = new JLabel("Nome de usuario:");
		lblNomeUsuario.setBounds(55, 40, 161, 14);
		painelBV.add(lblNomeUsuario);
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setBounds(55, 54, 161, 20);
		painelBV.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(55, 85, 46, 14);
		painelBV.add(lblSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos() && validarSenhaUsuario()) {
					
					JOptionPane.showMessageDialog(btnEntrar, "Login realizado!", "Aviso", JOptionPane.WARNING_MESSAGE);
					abrirImagem(txtNomeUsuario.getText());
					abrirAgenda(txtNomeUsuario.getText()); 
					
				}
			}
		});
		btnEntrar.setBounds(90, 146, 89, 30);
		painelBV.add(btnEntrar);
		
		fieldSenha = new JPasswordField();
		fieldSenha.setBounds(55, 100, 161, 23);
		painelBV.add(fieldSenha);
		
		JPanel painelCD = new JPanel();
		painelCD.setBackground(new Color(255, 255, 255));
		painelCD.setBounds(78, 263, 266, 78);
		contentPane.add(painelCD);
		painelCD.setLayout(null);
		painelCD.setBorder(BorderFactory.createTitledBorder(""));
		
		JLabel lblCadastro = new JLabel("Ainda não é cadastrado?");
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCadastro.setBounds(66, 11, 145, 14);
		painelCD.add(lblCadastro);
		
		JButton btnCliqueAqui = new JButton("Clique Aqui");
		btnCliqueAqui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				abrirCadastroUsuario();
			}
		});
		btnCliqueAqui.setBounds(76, 36, 110, 31);
		painelCD.add(btnCliqueAqui);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
