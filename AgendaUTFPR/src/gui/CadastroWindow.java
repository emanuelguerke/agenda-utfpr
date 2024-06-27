package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import entities.Imagem;
import entities.Usuario;
import service.ImagemService;
import service.UsuarioService;

public class CadastroWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeCompleto;
	private JFormattedTextField txtDataNascimento;
	private JTextField txtNomeUsuario;
	private JTextField txtEmail;
	private JPasswordField fieldSenha;
	
	private MaskFormatter mascaraData;
	private JPanel painelInfoPessoais;
	private ButtonGroup grupoBotao;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JRadioButton rbNaoInformar;
	private UsuarioService usuarioService;
	private JButton btnCadastrarUsuario;
	private FileInputStream fis;
	private int tamanho;
	
	private LoginWindow loginWindow;

	public CadastroWindow() {
		setTitle("Cadastro");
		
		this.criarMascaraData();
		this.iniciarComponentes();
		this.usuarioService = new UsuarioService();
		
	//	this.cadastrarUsuario();
	}
	
	private void voltarLogin() {
		
		LoginWindow voltar = new LoginWindow();
		voltar.setVisible(true);
		
		this.setVisible(false);
	}
	
	private void limparComponentes() {
		
		this.txtNomeCompleto.setText("");
		this.txtNomeUsuario.setText("");
		this.rbNaoInformar.setSelected(true);
		this.txtEmail.setText("");
		this.fieldSenha.setText("");
		this.txtDataNascimento.setText("");
	}
	
	private String verificarSelecaoSexo() {
		
		if (this.rbMasculino.isSelected()) {
					
					return "Masculino";
					
				} else if (this.rbFeminino.isSelected()) {
					
					return "Feminino";
					
				} else {
					
					return "Não informado";
				}
	}
	
	public boolean validarNomeUsuario() {
		
		try {
			
			if (this.usuarioService.validarNomeUsuario(txtNomeUsuario.getText())) {
				return true;
				
			} else {
				return false;
			}
			
		} catch (SQLException | IOException | NumberFormatException e) {
			
			System.out.println(e);
			return false;
		}
	}
	
	public static boolean validarEmail(String email) {
		
	    final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
	    return EMAIL_REGEX.matcher(email).matches();
	}
	
	public boolean validarCampos() {
		
		if ( validarEmail(txtEmail.getText()) && txtNomeCompleto.getText() != null && !txtNomeCompleto.getText().isEmpty() && txtNomeUsuario.getText() != null && !txtNomeUsuario.getText().isEmpty() && fieldSenha.getText() != null && !fieldSenha.getText().isEmpty()) {
			return true;
			
		} else {
			return false;
		}
		
	}
	
	private void criarMascaraData() {
		
		try {
			
			this.mascaraData = new MaskFormatter("##/##/####");
			
		} catch(ParseException e) {
			
			System.err.println("ERRO: " + e.getMessage());
		}
	}
	
	private void cadastrarUsuario() {
		
		try {
			
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				
				Usuario usuario = new Usuario();
				this.usuarioService = new UsuarioService();
				
				usuario.setNomeCompleto(this.txtNomeCompleto.getText());
				usuario.setDataNascimento(new java.sql.Date(formato.parse(this.txtDataNascimento.getText()).getTime()));
				usuario.setGenero(this.verificarSelecaoSexo());
				usuario.setNomeUsuario(this.txtNomeUsuario.getText());
				usuario.setEmail(this.txtEmail.getText());
				usuario.setSenha(this.fieldSenha.getText());
				
				this.usuarioService.cadastrar(usuario);
				this.limparComponentes();
			
			}
			catch (SQLIntegrityConstraintViolationException e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Nome de usuario já cadastrado.", "ERRO", JOptionPane.ERROR_MESSAGE);
				
			}
		    catch(ParseException e) {
				JOptionPane.showMessageDialog(null, "Data inválida.", "ERRO", JOptionPane.ERROR_MESSAGE);
				
		    }
			catch (SQLException | IOException e) {
				JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar um novo usuário.", "ERRO", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
			
			
	}
	
	private void iniciarComponentes() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 640);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmVoltar = new JMenuItem("Voltar");
		mntmVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltarLogin();
			}
		});
		menuBar.add(mntmVoltar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroUsuario = new JLabel("Cadastro de usuário");
		lblCadastroUsuario.setBounds(97, 11, 280, 32);
		lblCadastroUsuario.setFont(new Font("Tahoma", Font.BOLD, 26));
		contentPane.add(lblCadastroUsuario);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 54, 445, 2);
		contentPane.add(separator);
		
		painelInfoPessoais = new JPanel();
		painelInfoPessoais.setBounds(69, 60, 324, 264);
		contentPane.add(painelInfoPessoais);
		painelInfoPessoais.setLayout(null);
		painelInfoPessoais.setBorder(BorderFactory.createTitledBorder("Informações pessoais"));
		
		txtNomeCompleto = new JTextField();
		txtNomeCompleto.setBounds(144, 38, 146, 20);
		painelInfoPessoais.add(txtNomeCompleto);
		txtNomeCompleto.setColumns(10);
		
		JLabel lblNomeCompleto = new JLabel("Nome completo:");
		lblNomeCompleto.setBounds(24, 41, 103, 14);
		painelInfoPessoais.add(lblNomeCompleto);
		
		JLabel lblDataNascimento = new JLabel("Data de nascimento:");
		lblDataNascimento.setBounds(24, 84, 124, 14);
		painelInfoPessoais.add(lblDataNascimento);
		
		
		txtDataNascimento = new JFormattedTextField(this.mascaraData);
		txtDataNascimento.setBounds(144, 81, 66, 20);
		painelInfoPessoais.add(txtDataNascimento);
		txtDataNascimento.setColumns(10);
		
		JPanel painelGenero = new JPanel();
		painelGenero.setBounds(65, 133, 189, 105);
		painelInfoPessoais.add(painelGenero);
		painelGenero.setBorder(BorderFactory.createTitledBorder("Sexo"));
		painelGenero.setLayout(null);
		
		rbMasculino = new JRadioButton("Masculino");
		rbMasculino.setBounds(40, 18, 109, 23);
		painelGenero.add(rbMasculino);
		
		rbFeminino = new JRadioButton("Feminino");
		rbFeminino.setBounds(40, 44, 109, 23);
		painelGenero.add(rbFeminino);
		
		rbNaoInformar = new JRadioButton("Não infomar / Outro");
		rbNaoInformar.setBounds(40, 70, 143, 23);
		painelGenero.add(rbNaoInformar);
		
		grupoBotao = new ButtonGroup();
		grupoBotao.add(rbMasculino);
		grupoBotao.add(rbFeminino);
		grupoBotao.add(rbNaoInformar);
		
		JPanel painelInfoUsuario = new JPanel();
		painelInfoUsuario.setBounds(69, 335, 324, 168);
		contentPane.add(painelInfoUsuario);
		painelInfoUsuario.setLayout(null);
		painelInfoUsuario.setBorder(BorderFactory.createTitledBorder("Informações de usuário"));
		
		JLabel lblNomeUsuario = new JLabel("Nome de usuário:");
		lblNomeUsuario.setBounds(32, 32, 100, 14);
		painelInfoUsuario.add(lblNomeUsuario);
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setBounds(142, 29, 139, 20);
		painelInfoUsuario.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(32, 68, 46, 14);
		painelInfoUsuario.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(78, 65, 203, 20);
		painelInfoUsuario.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(32, 108, 46, 14);
		painelInfoUsuario.add(lblSenha);
		
		fieldSenha = new JPasswordField();
		fieldSenha.setBounds(78, 105, 203, 20);
		painelInfoUsuario.add(fieldSenha);
		
		btnCadastrarUsuario = new JButton("Cadastrar novo usuário");
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarCampos() && validarNomeUsuario()) {
					cadastrarUsuario();
					voltarLogin();
				} else
					JOptionPane.showMessageDialog(btnCadastrarUsuario, "Campo vazio ou invalido", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnCadastrarUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadastrarUsuario.setBounds(129, 509, 199, 39);
		contentPane.add(btnCadastrarUsuario);
	}
	
}
