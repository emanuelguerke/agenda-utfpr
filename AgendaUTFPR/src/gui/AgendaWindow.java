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
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;

public class AgendaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginWindow loginWindow;
	private String nome;
	private AgendaService agendaService;
	private JList lstAgenda;
	private int idUsuario;
	private int idAgenda;
	private String escolha;
	private JSeparator separator;
	private JTextField txtNome;
	private JLabel lblNewLabel;
	private JLabel lblNome;
	private JLabel lblDescricao;
	private JTextField txtDescricao;
	private JPanel panel_1;
	private JLabel lblExcluirAgenda;
	private JComboBox<String> cbAgenda;
	private JScrollPane scrollPane;
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
	
	private void abrirCompromissos(String nome, String nomeAgenda, int idUsuario) {
		
		CompromissoWindow compromissoWindow = new CompromissoWindow(nome, nomeAgenda, idUsuario);
		compromissoWindow.setVisible(true);
		
		this.setVisible(false);
		
	}
	
	private void limparComponentes() {
		
		this.txtNome.setText("");
		this.txtDescricao.setText("");
		//this.cbAgenda.setSelectedIndex(0);
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
				this.cbAgenda.removeAllItems();
			
			}
			catch (SQLException | IOException | NumberFormatException | NullPointerException e) {
				
				JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar uma nova agenda.", "ERRO", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
			
			
	}
	public boolean validarCampos() {
		
		if ( txtNome.getText() != null && !txtNome.getText().isEmpty() && txtDescricao.getText() != null && !txtDescricao.getText().isEmpty() ) {
			return true;
			
		} else {
			return false;
		}
		
	}
	
	void buscarAgendas() {
		
		try {
			idUsuario = buscarId(nome);
			//System.out.println(idUsuario);
			DefaultListModel DLM = new DefaultListModel<>();
			List<Agenda> agendas = this.agendaService.buscarAgendas(idUsuario);
			for (Agenda agenda : agendas) {
					this.cbAgenda.addItem(agenda.getNome());
					DLM.addElement(agenda.getNome());
			}		
			lstAgenda.setModel(DLM);
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao carregar os dados da agenda.", "Busca Agendas", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		
	}
	
	public int buscarId(String nome) {
		try {
			return agendaService.buscarId(nome);
			
		}catch(SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar usuario","Buscar ids", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		return idUsuario;
			
	}
	
	public int buscarIdAgenda(String nome) {
		
		try {
			return agendaService.buscarId(nome);
			
		}catch(SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar agenda","Buscar ids", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		return idAgenda;
			
	}
	
	private void excluirAgenda(String nomeAgenda) {
		
		try {
			
			idUsuario = buscarId(nome);
			Agenda agenda = new Agenda();
			this.agendaService = new AgendaService();
			
			this.agendaService.excluirAgenda(agenda, nomeAgenda,idUsuario);
		//	nome = txtNomeUsuario.getText();
			this.cbAgenda.removeAllItems();
			buscarAgendas();
			
		} catch (SQLException | IOException e) {
			
			JOptionPane.showMessageDialog(null, "Não foi possível excluir a agenda!");
		}
		
	}
	
	private void sairAgenda() {
		
		LoginWindow sairAgenda = new LoginWindow();
		sairAgenda.setVisible(true);
		
		this.setVisible(false);
	}
	
	public void iniciarComponentes() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 562, 642);
			setLocationRelativeTo(null);
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu mnOpcoes = new JMenu("Opções ");
			mnOpcoes.setForeground(new Color(0, 0, 0));
			mnOpcoes.setFont(new Font("Segoe UI", Font.BOLD, 12));
			menuBar.add(mnOpcoes);
			
			JMenuItem mntmPerfil = new JMenuItem("Visualizar perfil");
			mntmPerfil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			mntmPerfil.setBackground(new Color(255, 255, 255));
			mnOpcoes.add(mntmPerfil);
			
			JMenuItem mntmLogOut = new JMenuItem("Sair de sua conta ");
			mntmLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					sairAgenda();
				}
			});
			mnOpcoes.add(mntmLogOut);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			
			JButton btnAtualizar = new JButton("Atualizar Cadastro");
			btnAtualizar.setBounds(165, 530, 180, 23);
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirAtualizarCadastro(nome);
				}
			});
			contentPane.setLayout(null);
			contentPane.add(btnAtualizar);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(54, 261, 436, 258);
			contentPane.add(scrollPane);
			
			lstAgenda = new JList();
			scrollPane.setViewportView(lstAgenda);
			lstAgenda.setBorder(new LineBorder(new Color(0, 0, 0)));
			lstAgenda.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if(e.getValueIsAdjusting()){
					int index = lstAgenda.getSelectedIndex();
					Object nomeEscolha = lstAgenda.getSelectedValue();
				//	System.out.println(index);
				//	System.out.println(nomeEscolha.toString());
					abrirCompromissos(nome, nomeEscolha.toString(), idUsuario);
					}
				}
			});
			
			separator = new JSeparator();
			separator.setBounds(48, 248, 442, 2);
			contentPane.add(separator);
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(10, 11, 521, 111);
			contentPane.add(panel);
			panel.setLayout(null);
			
			txtNome = new JTextField();
			txtNome.setBounds(10, 42, 234, 20);
			panel.add(txtNome);
			txtNome.setColumns(10);
			
			lblNewLabel = new JLabel("Cadastrar nova agenda");
			lblNewLabel.setBounds(10, 0, 176, 14);
			panel.add(lblNewLabel);
			
			lblNome = new JLabel("Nome");
			lblNome.setBounds(10, 22, 46, 14);
			panel.add(lblNome);
			
			lblDescricao = new JLabel("Descrição");
			lblDescricao.setBounds(259, 22, 78, 14);
			panel.add(lblDescricao);
			
			txtDescricao = new JTextField();
			txtDescricao.setBounds(254, 42, 257, 20);
			panel.add(txtDescricao);
			txtDescricao.setColumns(10);
			
			JButton btnCadastrar = new JButton("Cadastrar");
			btnCadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validarCampos()) {
						cadastrarAgenda();
						buscarAgendas();
					}else {
						JOptionPane.showMessageDialog(btnCadastrar, "Campo vazio ou invalido", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			});
			btnCadastrar.setBounds(183, 73, 114, 23);
			panel.add(btnCadastrar);
			
			panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBounds(10, 133, 521, 111);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			lblExcluirAgenda = new JLabel("Excluir Agenda");
			lblExcluirAgenda.setBounds(10, 0, 87, 14);
			panel_1.add(lblExcluirAgenda);
			
			cbAgenda = new JComboBox<String>();
			cbAgenda.setBounds(90, 36, 342, 22);
			panel_1.add(cbAgenda);
			
			JButton btnExcluirAgenda = new JButton("Excluir Agenda");
			btnExcluirAgenda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cbAgenda.getSelectedItem() != null) {//verifica se o combobox está vazio antes de deletar
						
						String[] opcoes = {"Sim", "Não"}; 
						
						int opcao = JOptionPane.showOptionDialog(btnExcluirAgenda, "Deseja excluir a Agenda?", "Confirmação", JOptionPane.DEFAULT_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
						
						if (opcao == 0) {
							excluirAgenda(cbAgenda.getSelectedItem().toString());
							JOptionPane.showMessageDialog(btnExcluirAgenda, "Agenda excluída com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							
						} else if (opcao == 1) {
							JOptionPane.showMessageDialog(btnExcluirAgenda, "Operação cancelada!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						}
						
						
					}
					
				}
			});
			btnExcluirAgenda.setBounds(180, 69, 129, 23);
			panel_1.add(btnExcluirAgenda);
	}

	public AgendaWindow(String nomeUsuario) {
		setTitle("Agenda");
		
		this.nome = nomeUsuario;
		this.agendaService = new AgendaService();
		iniciarComponentes();
		buscarAgendas();
	
	
	
	}
}
