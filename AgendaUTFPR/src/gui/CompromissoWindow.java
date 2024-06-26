package gui;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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

import com.toedter.calendar.JDateChooser;

import entities.Agenda;
import entities.Compromisso;
import service.AgendaService;
import service.CompromissoService;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

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
	private MaskFormatter mascaraData; //tive que criar 2 mascaras pq só 1 bugava
	private MaskFormatter mascaraInicio;
	private MaskFormatter mascaraFim;
	private JTextField txtTitulo;
	private JTextField txtDescricao;
	private JTextField txtLocal;
	private JDateChooser dateChooserInicio;
	private JDateChooser dateChooserFim;
	private Date dataInicio;
	private Date dataFim;
	private String strDataInicio;
	private String strDataFim;
	private JLabel lblDataInicio;
	private JLabel lblDataFim;
	private JFormattedTextField txtHoraFim;
	private JFormattedTextField txtHoraInicio;
	private JComboBox cbCompromisso;
	private JPanel panel_3;
	private JButton btnExportar;
	private JButton btnImportar;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public void iniciarComponentes(String nomeAgenda, String descricaoAgenda) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 686, 832);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JPanel painelCompromissos = new JPanel();
			painelCompromissos.setBounds(0, 314, 653, 393);
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
			panel.setBounds(0, 11, 665, 49);
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
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBounds(0, 66, 663, 130);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblCadastrarCompromisso = new JLabel("Cadastrar novo compromisso");
			lblCadastrarCompromisso.setBounds(10, 0, 172, 14);
			panel_1.add(lblCadastrarCompromisso);
			
			JLabel lblTitulo = new JLabel("Titulo");
			lblTitulo.setBounds(10, 22, 72, 14);
			panel_1.add(lblTitulo);
			
			JLabel lblDescricao = new JLabel("Descrição");
			lblDescricao.setBounds(10, 48, 72, 14);
			panel_1.add(lblDescricao);
			
			JLabel lblLocal = new JLabel("Local");
			lblLocal.setBounds(10, 73, 72, 14);
			panel_1.add(lblLocal);
			
			txtTitulo = new JTextField();
			txtTitulo.setBounds(96, 19, 140, 20);
			panel_1.add(txtTitulo);
			txtTitulo.setColumns(10);
			
			txtDescricao = new JTextField();
			txtDescricao.setBounds(96, 45, 140, 20);
			panel_1.add(txtDescricao);
			txtDescricao.setColumns(10);
			
			txtLocal = new JTextField();
			txtLocal.setBounds(96, 70, 140, 20);
			panel_1.add(txtLocal);
			txtLocal.setColumns(10);
			
			JButton btnCadastrarCompromisso = new JButton("Cadastrar novo compromisso");
			btnCadastrarCompromisso.setBounds(211, 96, 255, 23);
			btnCadastrarCompromisso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validarCampos()) {
						cadastrarCompromisso();
						buscarCompromissos();
					}else {
						JOptionPane.showMessageDialog(btnCadastrarCompromisso, "Campo vazio ou invalido", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			});
			panel_1.add(btnCadastrarCompromisso);
			
			lblDataInicio = new JLabel("Data inicio");
			lblDataInicio.setBounds(290, 22, 88, 14);
			panel_1.add(lblDataInicio);
			dateChooserInicio = new JDateChooser(); //calendario inicio
			dateChooserInicio.setDateFormatString("dd/MM/yy");
			dateChooserInicio.setBounds(350, 22, 135, 20);
			panel_1.add(dateChooserInicio);
			
			lblDataFim = new JLabel("Data fim");
			lblDataFim.setBounds(290, 48, 88, 14);
			panel_1.add(lblDataFim);
			dateChooserFim = new JDateChooser(); //calendario fim
			dateChooserFim.setDateFormatString("dd/MM/yy");
			dateChooserFim.setBounds(350, 48, 135, 20);
			panel_1.add(dateChooserFim);
			
			JLabel lblHoraInicio = new JLabel("Hora inicio");
			lblHoraInicio.setBounds(495, 22, 71, 14);
			panel_1.add(lblHoraInicio);
			
			txtHoraInicio = new JFormattedTextField(mascaraInicio);
			txtHoraInicio.setBounds(586, 19, 67, 20);
			panel_1.add(txtHoraInicio);
			
			JLabel lblHoraFim = new JLabel("Hora fim");
			lblHoraFim.setBounds(495, 48, 71, 14);
			panel_1.add(lblHoraFim);
			
			txtHoraFim = new JFormattedTextField(mascaraFim);
			txtHoraFim.setBounds(586, 45, 67, 20);
			panel_1.add(txtHoraFim);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_2.setBounds(0, 202, 660, 101);
			contentPane.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblExcluirCompromisso = new JLabel("Excluir compromisso");
			lblExcluirCompromisso.setBounds(21, 0, 129, 14);
			panel_2.add(lblExcluirCompromisso);
			
			cbCompromisso = new JComboBox();
			cbCompromisso.setBounds(176, 33, 311, 22);
			panel_2.add(cbCompromisso);
			
			JButton btnExcluirCompromisso = new JButton("Excluir compromissos");
			btnExcluirCompromisso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cbCompromisso.getSelectedItem() != null) { //verifica se o combobox está vazio antes de deletar
						String[] opcoes = {"Sim", "Não"}; 
						
						int opcao = JOptionPane.showOptionDialog(btnExcluirCompromisso, "Deseja realmente excluir o compromisso?", "Confirmação", JOptionPane.DEFAULT_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
						
						if (opcao == 0) {
							excluirCompromisso(cbCompromisso.getSelectedItem().toString());
							JOptionPane.showMessageDialog(btnExcluirCompromisso, "Compromisso excluído com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							
						} else if (opcao == 1) {
							JOptionPane.showMessageDialog(btnExcluirCompromisso, "Operação cancelada!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						}
					}
						
				}
			});
			btnExcluirCompromisso.setBounds(258, 66, 175, 23);
			panel_2.add(btnExcluirCompromisso);
			
			panel_3 = new JPanel();
			panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.setBounds(0, 718, 660, 75);
			contentPane.add(panel_3);
			panel_3.setLayout(null);
			
			btnExportar = new JButton("Exportar");
			btnExportar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exportarCSV();
				}
			});
			btnExportar.setBounds(257, 11, 133, 23);
			panel_3.add(btnExportar);
			
			btnImportar = new JButton("Importar");
			btnImportar.setBounds(257, 41, 133, 23);
			panel_3.add(btnImportar);
	}
	
	public boolean validarCampos() {
		
		if ( txtTitulo.getText() != null && !txtTitulo.getText().isEmpty() && txtDescricao.getText() != null && !txtDescricao.getText().isEmpty() && txtHoraInicio.getText() != null && !txtHoraInicio.getText().isEmpty() && txtHoraFim.getText() != null && !txtHoraFim.getText().isEmpty() && dateChooserFim.getDate()!= null && dateChooserInicio.getDate() != null ) {
			return true;
			
		} else {
			return false;
		}
		
	}
	
	public void exportarCSV() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione o local para salvar");
        int userSelection = fileChooser.showSaveDialog(this); 
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave = fileChooser.getSelectedFile();
         
            try {
                  FileWriter fw = new FileWriter(fileToSave);
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < tblCompromissos.getRowCount(); i++) {
                    for (int j = 0; j<  tblCompromissos.getColumnCount(); j++) {
                        
                        bw.write(tblCompromissos.getValueAt(i, j).toString()+",");
                    }
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Salvo com sucesso","Salvo",JOptionPane.INFORMATION_MESSAGE);
                bw.close();
                fw.close();
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(this, "Erro","Erro",JOptionPane.ERROR_MESSAGE);
            }
            
            
        }
	}
	
	private void buscarCompromissos() {

		try {
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			DefaultTableModel modelo = (DefaultTableModel) tblCompromissos.getModel();
			modelo.fireTableDataChanged();
			modelo.setRowCount(0);
			
			List<Compromisso> compromissos = this.compromissoService.buscarCompromissos(idAgenda);
			
			for (Compromisso compromisso : compromissos) {
				this.cbCompromisso.addItem(compromisso.getTitulo());
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
			
			return compromissoService.buscarDescricaoAgenda(nomeAgenda, idUsuario);
			
		}catch(SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar agendas","agendas", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		return nomeAgenda;
			
	}
	
	public int buscarIdAgenda(String nomeAgenda, int idUsuario) {
		try {
			
			return compromissoService.buscarIdAgenda(nomeAgenda, idUsuario);
			
		}catch(SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar agenda","Buscar ids", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		return 0; 
			
	}
	
	private void criarMascaraInicio() {

		try {

			this.mascaraInicio = new MaskFormatter("##:##:##");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	
	private void criarMascaraFim() {

		try {

			this.mascaraFim = new MaskFormatter("##:##:##");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	private void limparComponentes() {

		this.txtTitulo.setText("");
		this.txtDescricao.setText("");
		
		this.dateChooserInicio.setDate(null);//conferir  se funciona mesmo
		this.dateChooserFim.setDate(null);
		
		this.txtHoraInicio.setText("");  
		this.txtHoraFim.setText("");
		this.txtLocal.setText("");
	}
	
	private void excluirCompromisso(String nomeCompromisso) {
		
		try {
		//	idAgenda = buscarIdAgenda(agendaNome, idUsuario);
		
			Compromisso compromisso = new Compromisso();
			this.compromissoService = new CompromissoService();
			
			this.compromissoService.excluirCompromisso(compromisso, nomeCompromisso,idAgenda);
		//	nome = txtNomeUsuario.getText();
			this.cbCompromisso.removeAllItems();
			buscarCompromissos();
			
		} catch (SQLException | IOException e) {
			
			JOptionPane.showMessageDialog(null, "Não foi possível excluir o compromisso!");
			System.out.println(e);
		}
		
	}
	private void cadastrarCompromisso() {
		
		try {
		//		idAgenda = buscarIdAgenda(agendaNome);
				Compromisso compromisso = new Compromisso();
				this.compromissoService = new CompromissoService();
				
				compromisso.setTitulo(this.txtTitulo.getText());
				compromisso.setDescricao(this.txtDescricao.getText());
				
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
				dataInicio = sdf.parse(this.dateChooserInicio.getDate().toString());
				dataFim = sdf.parse(this.dateChooserFim.getDate().toString());
				SimpleDateFormat sdf2=new SimpleDateFormat("dd/MM/yy");
				strDataInicio = sdf2.format(dataInicio);
				strDataFim = sdf2.format(dataFim);
				dataInicio= sdf2.parse(strDataInicio);
				dataFim= sdf2.parse(strDataFim);
				
				
				compromisso.setDataInicio(dataInicio);
				compromisso.setDataFim(dataFim);
				
				SimpleDateFormat sdfTempo = new SimpleDateFormat("HH:mm:ss");

				compromisso.setHoraInicio(txtHoraInicio.getText());
				compromisso.setHoraFim(txtHoraFim.getText());
				compromisso.setLocal(txtLocal.getText());
			 

				
			//Não funcionou com o java.sql.date	
			//	System.out.println(new java.sql.Date(sdf.parse(this.dateChooserInicio.getDate()).getTime()));
				this.compromissoService.cadastrar(compromisso, idAgenda);
				this.limparComponentes();
				this.cbCompromisso.removeAllItems();
				
			
			}
			catch ( NumberFormatException | NullPointerException | ParseException | SQLException | IOException e) {
				
				JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar uma novo compromisso.", "ERRO", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
			
			
	}
	
	private void finalizarAplicacao() {

		System.exit(0);
	}
	
	public CompromissoWindow(String nomeAgenda, int idUsuario) {
			criarMascaraInicio();
			criarMascaraFim();
			this.compromissoService = new CompromissoService();
			this.agendaNome = nomeAgenda;
			this.idAgenda = buscarIdAgenda(nomeAgenda, idUsuario);
			iniciarComponentes(nomeAgenda,buscarDescricaoAgenda(nomeAgenda,idUsuario));
			buscarCompromissos();
			
			
			
		//	this.limparComponentes();

	}
}
