package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import entities.Usuario;
import service.UsuarioService;
import gui.LoginWindow;

public class MenuPrincipalWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Usuario usuario;
	private UsuarioService usuarioService;
	private LoginWindow loginWndow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipalWindow frame = new MenuPrincipalWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBemVindo = new JLabel("Bem Vindo a agenda");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblBemVindo.setBounds(10, 11, 414, 206);
		contentPane.add(lblBemVindo);
	}
	
	public MenuPrincipalWindow() {
		iniciarComponentes();
		
	}
}
