package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class AtualizarCadastroWindow extends JFrame {

	private JPanel contentPane;
	
	private LoginWindow loginWindow;
	private AgendaWindow agendaWindow;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public AtualizarCadastroWindow() {
		
		this.iniciarComponentes();
	}
	
	private void voltarAgenda() {
		
		AgendaWindow agendaWindow = new AgendaWindow();
		agendaWindow.setVisible(true);
		
		this.dispose();
	}
	
	private void atualizarDados() {
		
	}
	
	private void iniciarComponentes() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				voltarAgenda();
			}
		});
		btnVoltar.setBounds(10, 11, 73, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblTitulo = new JLabel("Atualizar dados");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setBounds(129, 11, 233, 31);
		contentPane.add(lblTitulo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 48, 446, 2);
		contentPane.add(separator);
		
		JPanel painelInfoUsuarios = new JPanel();
		painelInfoUsuarios.setBounds(10, 61, 482, 263);
		contentPane.add(painelInfoUsuarios);
		painelInfoUsuarios.setLayout(null);
		painelInfoUsuarios.setBorder(BorderFactory.createTitledBorder("Informações pessoais"));
		
		JLabel lblNomeCompleto = new JLabel("Nome completo:");
		lblNomeCompleto.setBounds(44, 60, 86, 14);
		painelInfoUsuarios.add(lblNomeCompleto);
		
		JLabel lblNomeUsuario = new JLabel("Nome usuario:");
		lblNomeUsuario.setBounds(44, 85, 73, 14);
		painelInfoUsuarios.add(lblNomeUsuario);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(89, 113, 28, 14);
		painelInfoUsuarios.add(lblEmail);
		
		JLabel lblDataNascimento = new JLabel("Data nascimento:");
		lblDataNascimento.setBounds(31, 138, 86, 14);
		painelInfoUsuarios.add(lblDataNascimento);
		
		textField = new JTextField();
		textField.setBounds(127, 57, 151, 20);
		painelInfoUsuarios.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(127, 82, 151, 20);
		painelInfoUsuarios.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(127, 110, 151, 20);
		painelInfoUsuarios.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(127, 135, 86, 20);
		painelInfoUsuarios.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel painelSexo = new JPanel();
		painelSexo.setBounds(299, 57, 157, 110);
		painelInfoUsuarios.add(painelSexo);
		painelSexo.setLayout(null);
		painelSexo.setBorder(BorderFactory.createTitledBorder("Gênero"));
		
		JRadioButton rbMasculino = new JRadioButton("Masculino");
		rbMasculino.setBounds(16, 20, 109, 23);
		painelSexo.add(rbMasculino);
		
		JRadioButton rbFeminino = new JRadioButton("Feminino");
		rbFeminino.setBounds(16, 46, 109, 23);
		painelSexo.add(rbFeminino);
		
		JRadioButton rbNaoInformar = new JRadioButton("Não informar / Outro");
		rbNaoInformar.setBounds(16, 72, 135, 23);
		painelSexo.add(rbNaoInformar);
		
		JButton btnAtualizarDados = new JButton("Atualizar dados");
		btnAtualizarDados.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAtualizarDados.setBounds(168, 199, 127, 41);
		painelInfoUsuarios.add(btnAtualizarDados);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarCadastroWindow frame = new AtualizarCadastroWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
