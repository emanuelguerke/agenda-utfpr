package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import service.AgendaService;
import entities.Agenda;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class AgendaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginWindow loginWindow;
	private String nome;
	private AgendaService agendaService;
	private JList lstAgenda;
	private int idUsuario;
	private String escolha;
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
			setBounds(100, 100, 545, 506);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			
			JButton btnAtualizar = new JButton("Atualizar");
			btnAtualizar.setBounds(172, 11, 180, 23);
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirAtualizarCadastro(nome);
				}
			});
			contentPane.setLayout(null);
			contentPane.add(btnAtualizar);
			
			lstAgenda = new JList();
			lstAgenda.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					int index = lstAgenda.getSelectedIndex();
					Object nomeEscolha = lstAgenda.getSelectedValue();
					System.out.println(index);
					System.out.println(nomeEscolha);
	//				abrirCompromissos();
				}
			});
			lstAgenda.setBounds(54, 145, 438, 293);
			contentPane.add(lstAgenda);
	}

	public AgendaWindow(String nomeUsuario) {
		this.nome = nomeUsuario;
		this.agendaService = new AgendaService();
		iniciarComponentes();
		buscarAgendas();
	
	
	
	}
}
