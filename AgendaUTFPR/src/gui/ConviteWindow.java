package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import entities.Compromisso;
import service.CompromissoService;
import service.ConviteService;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class ConviteWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox cbCompromisso;
	private JComboBox cbNomeUsuario;
	
	private CompromissoService compromissoService;
	private ConviteService conviteService;
	

	public ConviteWindow() {
		iniciarComponentes();
	}
	
	public void convidar() {
		try {
			Compromisso compromisso = new Compromisso();
			this.compromissoService = new CompromissoService();
			this.conviteService = new ConviteService();
			this.conviteService.convidar();
		}catch(SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível convidar o usuario");
		}
	}
	
	public void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConvidar = new JLabel("Convidar para compromisso");
		lblConvidar.setBounds(10, 21, 173, 14);
		contentPane.add(lblConvidar);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 469, 242);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNomeDoUsuario = new JLabel("Selecione o nome do usuario");
		lblNomeDoUsuario.setBounds(150, 9, 191, 14);
		panel.add(lblNomeDoUsuario);
		
		cbNomeUsuario = new JComboBox();
		cbNomeUsuario.setBounds(105, 34, 223, 22);
		panel.add(cbNomeUsuario);
		
		JLabel lblCompromisso = new JLabel("Selecione o compromisso");
		lblCompromisso.setBounds(150, 67, 178, 14);
		panel.add(lblCompromisso);
		
		cbCompromisso = new JComboBox();
		cbCompromisso.setBounds(104, 100, 224, 22);
		panel.add(cbCompromisso);
		
		JButton btnConvidar = new JButton("Convidar");
		btnConvidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convidar();
			}
		});
		btnConvidar.setBounds(169, 157, 107, 23);
		panel.add(btnConvidar);
	}
	
}
