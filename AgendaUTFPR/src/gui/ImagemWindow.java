package gui;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import entities.Imagem;
import service.ImagemService;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImagemWindow extends JFrame {

	private JPanel contentPane;
	
	private ImagemService imagemService;
	JFileChooser foto = new JFileChooser();
	private int idUsuario;
	private String nome;
	private JLabel lblFotoPerfil;

	public ImagemWindow(String nomeUsuario) {
		setTitle("Foto de perfil");
		this.nome= nomeUsuario;
		this.iniciarComponentes();
	}
	
	public void fecharFoto() {
		this.dispose();
	}
	
	public int buscarId(String nome) {
		
		try {
			
			this.imagemService = new ImagemService();
			System.out.println(nome);
			
			return imagemService.buscarId(nome);
			
		}catch (SQLException | IOException e) {
			
			JOptionPane.showMessageDialog(null, "Erro ao carregar usuario","Buscar id's", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
		
		return idUsuario;
			
	}
	
	private void nomeImagem() {
		
		try {
			
			idUsuario = buscarId(nome);
			
			Imagem imagem = new Imagem();
			this.imagemService = new ImagemService();
			
			imagem.setNome((String)foto.getSelectedFile().getAbsolutePath());
			System.out.println(foto.getSelectedFile().getAbsolutePath());

			this.imagemService.nomeImagem(imagem, idUsuario);
			
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private void carregarFoto() {
		
		foto.setDialogTitle("Selecionar arquivo");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo de imagens (*.PNG,*.JPG,*.JPEG) ", "png","jpg","jpeg");
		foto.setFileFilter(filtro);
		
		int resultado = foto.showOpenDialog(null);
		
		if (resultado == JFileChooser.APPROVE_OPTION) {
			
			try {
				
				FileInputStream fis = new FileInputStream(foto.getSelectedFile());
				int tamanho = (int) foto.getSelectedFile().length();
				
				Image fotoPerfil = ImageIO.read(foto.getSelectedFile()).getScaledInstance(lblFotoPerfil.getWidth(),
												lblFotoPerfil.getHeight(), Image.SCALE_SMOOTH);
				lblFotoPerfil.setIcon(new ImageIcon(fotoPerfil));
				lblFotoPerfil.updateUI();
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Erro ao adicionar a imagem", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void iniciarComponentes() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 49, 385, 1);
		contentPane.add(separator);
		
		JLabel lblTituloPerfil = new JLabel("Foto de perfil");
		lblTituloPerfil.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblTituloPerfil.setBounds(105, 11, 206, 27);
		contentPane.add(lblTituloPerfil);
		
		JPanel painelFoto = new JPanel();
		painelFoto.setBounds(105, 75, 181, 153);
		contentPane.add(painelFoto);
		painelFoto.setLayout(null);
		painelFoto.setBorder(BorderFactory.createTitledBorder(""));
		
		lblFotoPerfil = new JLabel("");
		lblFotoPerfil.setBounds(10, 11, 161, 131);
		painelFoto.add(lblFotoPerfil);
		lblFotoPerfil.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnAddFoto = new JButton("Adicionar foto");
		btnAddFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				carregarFoto();
				nomeImagem();
				fecharFoto();
				
				
			}
		});
		btnAddFoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddFoto.setBounds(122, 258, 150, 27);
		contentPane.add(btnAddFoto);
		
		
	}
	

}
