package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import service.AgendaService;
import service.UsuarioService;
import entities.Agenda;
import entities.Usuario;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class AgendaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginWindow loginWindow;
	private String nome;
	private AgendaService agendaService;
	private JList lstAgenda;
	private int idUsuario;
	private String escolha;
	private JSeparator separator;
	private JTextField txtNome;
	private JLabel lblNewLabel;
	private JLabel lblNome;
	private JLabel lblDescricao;
	private JTextField txtDescricao;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	private void abrirAtualizarCadastro(String nome) {
		
		AtualizarCadastroWindow atualizarCadastroWindow = new AtualizarCadastroWindow(nome);
		atualizarCadastroWindow.setVisible(true);
		
		this.setVisible(false);
		
	}
	private void abrirCompromissos() {
		
		CompromissoWindow compromissoWindow = new CompromissoWindow();
		compromissoWindow.setVisible(true);
		
		this.setVisible(false);
		
	}
	
	private void limparComponentes() {
		
		this.txtNome.setText("");
		this.txtDescricao.setText("");
	}
	
	private void cadastrarAgenda() {
		
		try {
				idUsuario = buscarId(nome);
				Agenda agenda = new Agenda();
				this.agendaService = new AgendaService();
				
				agenda.setNome(this.txtNome.getText());
				agenda.setDescricao(this.txtDescricao.getText());
				
				
				this.agendaService.cadastrar(agenda, idUsuario);
				this.limparComponentes();
			
			}
			catch (SQLException | IOException e) {
				
				JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar uma nova agenda.", "ERRO", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
			
			
	}
	
	private void buscarAgendas() {
		
		try {
			idUsuario = buscarId(nome);
			//System.out.println(idUsuario);
			DefaultListModel DLM = new DefaultListModel<>();
			List<Agenda> agendas = this.agendaService.buscarAgendas(idUsuario);
			for (Agenda agenda : agendas) {
					DLM.addElement(agenda.getNome());
					System.out.println(agenda.getNome());
			}		
			lstAgenda.setModel(DLM);
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao carregar os dados da agenda.", "Busca Agendas", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		
	}
	public int buscarId(String nome) {
		try {
			System.out.println(nome);
			return agendaService.buscarId(nome);
			
		}catch(SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar usuario","Buscar ids", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		return idUsuario;
			
	}
	
	public void iniciarComponentes() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 554, 545);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			
			JButton btnAtualizar = new JButton("Atualizar Cadastro");
			btnAtualizar.setBounds(163, 460, 180, 23);
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirAtualizarCadastro(nome);
				}
			});
			contentPane.setLayout(null);
			contentPane.add(btnAtualizar);
			
			lstAgenda = new JList();
			lstAgenda.setBorder(new LineBorder(new Color(0, 0, 0)));
			lstAgenda.setBounds(54, 180, 436, 258);
			lstAgenda.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					int index = lstAgenda.getSelectedIndex();
					Object nomeEscolha = lstAgenda.getSelectedValue();
					System.out.println(index);
					System.out.println(nomeEscolha);
	//				abrirCompromissos();
				}
			});
			contentPane.add(lstAgenda);
			
			separator = new JSeparator();
			separator.setBounds(54, 167, 442, 2);
			contentPane.add(separator);
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(46, 11, 450, 145);
			contentPane.add(panel);
			panel.setLayout(null);
			
			txtNome = new JTextField();
			txtNome.setBounds(92, 34, 252, 20);
			panel.add(txtNome);
			txtNome.setColumns(10);
			
			lblNewLabel = new JLabel("Cadastrar nova agenda");
			lblNewLabel.setBounds(10, 0, 176, 14);
			panel.add(lblNewLabel);
			
			lblNome = new JLabel("Nome:");
			lblNome.setBounds(10, 37, 46, 14);
			panel.add(lblNome);
			
			lblDescricao = new JLabel("Descrição:");
			lblDescricao.setBounds(10, 62, 78, 14);
			panel.add(lblDescricao);
			
			txtDescricao = new JTextField();
			txtDescricao.setBounds(92, 59, 252, 20);
			panel.add(txtDescricao);
			txtDescricao.setColumns(10);
			
			JButton btnCadastrar = new JButton("Cadastrar");
			btnCadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cadastrarAgenda();
					buscarAgendas();
				}
			});
			btnCadastrar.setBounds(154, 99, 114, 23);
			panel.add(btnCadastrar);
	}

	public AgendaWindow(String nomeUsuario) {
		this.nome = nomeUsuario;
		this.agendaService = new AgendaService();
		iniciarComponentes();
		buscarAgendas();
	
	
	
	}
}
