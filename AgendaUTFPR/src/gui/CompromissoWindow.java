package gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entities.Agenda;
import entities.Compromisso;
import service.AgendaService;
import service.CompromissoService;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CompromissoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblCompromissos;
	private JLabel lblNomeAgenda;
	private JLabel lblDescricaoAgenda;
	private CompromissoService compromissoService;
	private JLabel lblNomeDaAgenda;
	private JLabel lblDescricaoDaAgenda;
	private String agendaNome;
	private int idAgenda;
	private MaskFormatter mascaraData;


	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public void iniciarComponentes(String nomeAgenda, String descricaoAgenda) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 689, 607);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JPanel painelCompromissos = new JPanel();
			painelCompromissos.setBounds(10, 147, 653, 393);
			contentPane.add(painelCompromissos);
			painelCompromissos.setLayout(null);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 5, 643, 427);
			painelCompromissos.add(scrollPane_1);
			
			tblCompromissos = new JTable();
			tblCompromissos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"T\u00EDtulo", "Descri\u00E7\u00E3o", "Data Inicio", "Data fim", "Local", "Hora inicio", "Hora fim"
				}
			));
			scrollPane_1.setViewportView(tblCompromissos);
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(0, 11, 663, 118);
			contentPane.add(panel);
			panel.setLayout(null);
			
			lblNomeAgenda = new JLabel(nomeAgenda);
			lblNomeAgenda.setBounds(157, 0, 325, 14);
			panel.add(lblNomeAgenda);
			
			lblDescricaoAgenda = new JLabel(descricaoAgenda);
			lblDescricaoAgenda.setBounds(157, 25, 502, 14);
			panel.add(lblDescricaoAgenda);
			
			lblNomeDaAgenda = new JLabel("Nome da Agenda");
			lblNomeDaAgenda.setBounds(10, 0, 112, 14);
			panel.add(lblNomeDaAgenda);
			
			lblDescricaoDaAgenda = new JLabel("Descricão da agenda");
			lblDescricaoDaAgenda.setBounds(10, 25, 131, 14);
			panel.add(lblDescricaoDaAgenda);
	}
	
	private void buscarCompromissos() {

		try {
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			DefaultTableModel modelo = (DefaultTableModel) tblCompromissos.getModel();
			modelo.fireTableDataChanged();
			modelo.setRowCount(0);
			
			List<Compromisso> compromissos = this.compromissoService.buscarCompromissos(idAgenda);
			System.out.println("teste compromisso" +idAgenda);
			for (Compromisso compromisso : compromissos) {
				
				modelo.addRow(new Object[] { 
					compromisso.getTitulo(), 
					compromisso.getDescricao(), 
					formato.format(compromisso.getDataInicio()), 
					formato.format(compromisso.getDataFim()),
					compromisso.getLocal(),
					compromisso.getHoraInicio(), 
					compromisso.getHoraFim()
				});
			}
		
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos compromissos.", "Busca de compromissos", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
	}
	
	public String buscarDescricaoAgenda(String nomeAgenda, int idUsuario) {
		try {
			System.out.println(nomeAgenda);
			return compromissoService.buscarDescricaoAgenda(nomeAgenda, idUsuario);
			
		}catch(SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar agendas","agendas", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		return nomeAgenda;
			
	}
	public int buscarIdAgenda(String nomeAgenda) {
		try {
			System.out.println(nomeAgenda);
			return compromissoService.buscarIdAgenda(nomeAgenda);
			
		}catch(SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar agenda","Buscar ids", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		return 0; 
			
	}
	
	
	public CompromissoWindow(String nomeAgenda, int idUsuario) {
			this.compromissoService = new CompromissoService();
			this.agendaNome = nomeAgenda;
			this.idAgenda = buscarIdAgenda(nomeAgenda);
			System.out.println(idAgenda);
			iniciarComponentes(nomeAgenda,buscarDescricaoAgenda(nomeAgenda,idUsuario));
			buscarCompromissos();
	}
}
