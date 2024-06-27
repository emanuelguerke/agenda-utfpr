package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import entities.Usuario;
import service.UsuarioService;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

public class AtualizarCadastroWindow extends JFrame {

	private JPanel contentPane;
	
	private LoginWindow loginWindow;
	private AgendaWindow agendaWindow;
	private JTextField txtNomeCompleto;
	private JTextField txtNomeUsuario;
	private JTextField txtEmail;
	private String nome;
	private MaskFormatter mascaraData;
	private JPasswordField fieldSenha;
	private UsuarioService usuarioService;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JRadioButton rbNaoInformar;
	private JFormattedTextField txtDataNascimento;
	private ButtonGroup grupoBotao;
	
	public AtualizarCadastroWindow(String nomeUsuario) {
		setTitle("Atualizar dados");
		
		this.nome = nomeUsuario;
		this.criarMascaraData();
		
		usuarioService = new UsuarioService();
		this.iniciarComponentes();
		
		loginWindow = new LoginWindow();
		System.out.println("teste");
		System.out.println(nome);
	}
	
	private void voltarLogin() {
		
		LoginWindow voltar = new LoginWindow();
		voltar.setVisible(true);
		
		this.dispose();
	}
	
	public boolean validarCampos() {
		if( validarEmail(txtEmail.getText()) && txtNomeCompleto.getText() != null && !txtNomeCompleto.getText().isEmpty() && txtNomeUsuario.getText() != null && !txtNomeUsuario.getText().isEmpty() && fieldSenha.getText() != null && !fieldSenha.getText().isEmpty()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static boolean validarEmail(String email) {
		
	    final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
	    return EMAIL_REGEX.matcher(email).matches();
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
	
	private void criarMascaraData() {
		
		try {
			
			this.mascaraData = new MaskFormatter("##/##/####");
			
		} catch(ParseException e) {
			
			System.err.println("ERRO: " + e.getMessage());
		}
	}
	
	private void voltarAgenda() {
		
		AgendaWindow agendaWindow = new AgendaWindow(nome);
		agendaWindow.setVisible(true);
		
		this.dispose();
	}
	
	private void limparComponentes() {
		
		this.txtNomeCompleto.setText("");
		this.txtNomeUsuario.setText("");
		this.rbNaoInformar.setSelected(true);
		this.txtEmail.setText("");
		this.fieldSenha.setText("");
		this.txtDataNascimento.setText("");
		
	}
	
	private void excluirUsuario() {
	
		try {
			
			Usuario usuario = new Usuario();
			this.usuarioService = new UsuarioService();
			
			this.usuarioService.excluirUsuario(usuario, nome);
			nome = txtNomeUsuario.getText();
			
		} catch (SQLException | IOException e) {
			
			JOptionPane.showMessageDialog(null, "Não foi possível excluir o usuario!");
		}
		
	}
	
	private void atualizarUsuario() {
		
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
				
				this.usuarioService.atualizarUsuario(usuario, nome);
				nome = 	txtNomeUsuario.getText();
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
		setBounds(100, 100, 518, 511);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltarAgenda();
			}
		});
		btnVoltar.setBounds(10, 11, 73, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblTitulo = new JLabel("Atualizar dados");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setBounds(130, 11, 233, 31);
		contentPane.add(lblTitulo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 482, 2);
		contentPane.add(separator);
		
		JPanel painelInfoUsuarios = new JPanel();
		painelInfoUsuarios.setBounds(10, 61, 482, 263);
		contentPane.add(painelInfoUsuarios);
		painelInfoUsuarios.setLayout(null);
		painelInfoUsuarios.setBorder(BorderFactory.createTitledBorder("Informações pessoais"));
		
		JLabel lblNomeCompleto = new JLabel("Nome completo:");
		lblNomeCompleto.setBounds(10, 60, 98, 14);
		painelInfoUsuarios.add(lblNomeCompleto);
		
		JLabel lblNomeUsuario = new JLabel("Nome usuario:");
		lblNomeUsuario.setBounds(10, 85, 86, 14);
		painelInfoUsuarios.add(lblNomeUsuario);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 113, 86, 14);
		painelInfoUsuarios.add(lblEmail);
		
		JLabel lblDataNascimento = new JLabel("Data nascimento:");
		lblDataNascimento.setBounds(10, 138, 86, 14);
		painelInfoUsuarios.add(lblDataNascimento);
		
		txtNomeCompleto = new JTextField();
		txtNomeCompleto.setBounds(127, 57, 151, 20);
		painelInfoUsuarios.add(txtNomeCompleto);
		txtNomeCompleto.setColumns(10);
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setBounds(127, 82, 151, 20);
		painelInfoUsuarios.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(127, 110, 151, 20);
		painelInfoUsuarios.add(txtEmail);
		txtEmail.setColumns(10);
		
		JPanel painelSexo = new JPanel();
		painelSexo.setBounds(299, 57, 157, 110);
		painelInfoUsuarios.add(painelSexo);
		painelSexo.setLayout(null);
		painelSexo.setBorder(BorderFactory.createTitledBorder("Gênero"));
		
		rbMasculino = new JRadioButton("Masculino");
		rbMasculino.setBounds(16, 20, 109, 23);
		painelSexo.add(rbMasculino);
		
		rbFeminino = new JRadioButton("Feminino");
		rbFeminino.setBounds(16, 46, 109, 23);
		painelSexo.add(rbFeminino);
		
		rbNaoInformar = new JRadioButton("Não informar / Outro");
		rbNaoInformar.setBounds(16, 72, 135, 23);
		painelSexo.add(rbNaoInformar);
		
		grupoBotao = new ButtonGroup();
		grupoBotao.add(rbMasculino);
		grupoBotao.add(rbFeminino);
		grupoBotao.add(rbNaoInformar);
		
		JButton btnAtualizarDados = new JButton("Atualizar dados");
		btnAtualizarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarCampos() && validarNomeUsuario())
					atualizarUsuario();
					else
						JOptionPane.showMessageDialog(btnAtualizarDados, "Campo vazio ou invalido", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnAtualizarDados.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAtualizarDados.setBounds(168, 199, 151, 41);
		painelInfoUsuarios.add(btnAtualizarDados);
		
		txtDataNascimento = new JFormattedTextField(this.mascaraData);
		txtDataNascimento.setBounds(127, 135, 151, 20);
		painelInfoUsuarios.add(txtDataNascimento);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 164, 46, 14);
		painelInfoUsuarios.add(lblSenha);
		
		fieldSenha = new JPasswordField();
		fieldSenha.setBounds(127, 161, 151, 20);
		painelInfoUsuarios.add(fieldSenha);
		
		JPanel painelExcluir = new JPanel();
		painelExcluir.setBounds(10, 329, 482, 132);
		contentPane.add(painelExcluir);
		painelExcluir.setLayout(null);
		painelExcluir.setBorder(BorderFactory.createTitledBorder("Excluir conta"));
		
		JLabel lblExcluir = new JLabel("Deseja excluir a conta?");
		lblExcluir.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblExcluir.setBounds(133, 23, 191, 14);
		painelExcluir.add(lblExcluir);
		
		JLabel lblCliqueAqui = new JLabel("Clique aqui:");
		lblCliqueAqui.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCliqueAqui.setBounds(186, 48, 68, 14);
		painelExcluir.add(lblCliqueAqui);
		
		JButton btnExcluir = new JButton("Excluir ");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] opcoes = {"Sim", "Não"}; 
				
				int opcao = JOptionPane.showOptionDialog(btnExcluir, "Deseja excluir seu usuário?", "Confirmação", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
				
				if (opcao == 0) {
					excluirUsuario();
					JOptionPane.showMessageDialog(btnExcluir, "Usuário excluído com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					voltarLogin();
					
				} else if (opcao == 1) {
					JOptionPane.showMessageDialog(btnExcluir, "Operação cancelada!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExcluir.setBounds(167, 73, 109, 37);
		painelExcluir.add(btnExcluir);
	}
}
