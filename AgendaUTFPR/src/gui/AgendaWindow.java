package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgendaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginWindow loginWindow;
	private String nome;
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
	public AgendaWindow(String nomeUsuario) {
		this.nome = nomeUsuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(130, 10, 180, 23);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirAtualizarCadastro(nome);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnAtualizar);
	}

}
