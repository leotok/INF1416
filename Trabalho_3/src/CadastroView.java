import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class CadastroView extends JFrame {

	private final int width = 450;
	private final int height = 630;
	
	private HashMap user = null;
	
	public CadastroView(HashMap user) {
		this.user = user;
		DBManager.insereRegistro(6001, (String) user.get("email"));
		
		setLayout(null);
		setSize (this.width, this.height);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setTitle("Cadastro");
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		
		Container c = getContentPane();
		
		c.add(new Header((String)user.get("email"), (String)user.get("groupName"), (String)user.get("name")));
		c.add(new FirstBody("Total de usuários do sistema", DBManager.retornaNumUsuarios()));
		
		
		JLabel certificadoDigitalLabel = new JLabel();
		certificadoDigitalLabel .setBounds(30, 130, 300, 30);
		c.add(certificadoDigitalLabel);
		JButton certificadoDigitalButton = new JButton("Escolha o arquivo do Certificado Digital");
		certificadoDigitalButton .setBounds(30, 170, 300, 30);
		c.add(certificadoDigitalButton);
		certificadoDigitalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser certificadoDigitalchooser = new JFileChooser(); 
				certificadoDigitalchooser.setCurrentDirectory(new java.io.File("."));
				certificadoDigitalchooser.setDialogTitle("Caminho do Certificado Digital");
				certificadoDigitalchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				if (certificadoDigitalchooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					certificadoDigitalLabel.setText(certificadoDigitalchooser.getSelectedFile().getAbsolutePath());
				}
			    else {
			      System.out.println("No Selection ");
			    }
			}
		});
		
		JLabel tanListLabel = new JLabel();
		tanListLabel .setBounds(30, 210, 300, 30);
		c.add(tanListLabel);
		JButton tanListButton = new JButton("Escolha uma pasta para a TAN List");
		c.add(tanListButton);
		tanListButton .setBounds(30, 250, 300, 30);
		tanListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser tanListchooser = new JFileChooser(); 
				tanListchooser.setCurrentDirectory(new java.io.File("."));
				tanListchooser.setDialogTitle("Caminho da TAN List");
				tanListchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				if (tanListchooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					tanListLabel.setText(tanListchooser.getSelectedFile().getAbsolutePath());
				}
			    else {
			      System.out.println("No Selection ");
			    }
			}
		});
		
		JLabel grupoLabel = new JLabel("Grupo:");
		grupoLabel.setBounds(30, 290, 300, 40);
		c.add(grupoLabel);
		String[] choices = {"Usuario", "Administrador"};
		JComboBox<String> comboBox = new JComboBox<String>(choices);
		comboBox .setBounds(30, 330, 300, 40);
		comboBox.setVisible(true);
		c.add(comboBox );
		
		JLabel senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(30, 370, 300, 40);
		c.add(senhaLabel);
		JPasswordField senhaField = new JPasswordField(); 
		senhaField.setBounds(30, 410, 300, 40);
		c.add(senhaField);
		
		JLabel senhaConfirmacaoLabel = new JLabel("Confirme a senha:");
		senhaConfirmacaoLabel.setBounds(30, 450, 300, 40);
		c.add(senhaConfirmacaoLabel);
		JPasswordField senhaConfirmacaoField = new JPasswordField(); 
		senhaConfirmacaoField.setBounds(30, 490, 300, 40);
		c.add(senhaConfirmacaoField);
		
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(30, 530, 300, 40);
		c.add(cadastrarButton);
		cadastrarButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				DBManager.insereRegistro(6002, (String) user.get("email"));
			
				String senha = new String( senhaField.getPassword());
				String confirmacao = new String(senhaConfirmacaoField.getPassword());
				String grupo = (String) comboBox.getSelectedItem().toString().toLowerCase();
				
				Path cdPath = Paths.get(certificadoDigitalLabel.getText());
				byte[] certDigBytes = null;
				try {
					certDigBytes = Files.readAllBytes(cdPath);
				} catch (Exception a) {
					a.printStackTrace();
					DBManager.insereRegistro(6004, (String) user.get("email"));
					return;
				}
				
				X509Certificate cert = Auth.leCertificadoDigital(certDigBytes);
				if (cert == null) {
					DBManager.insereRegistro(6004, (String) user.get("email"));
					return;
				}
				String infoString = cert.getVersion() +"\n"+ cert.getNotBefore() +"\n"+ cert.getType() +"\n"+ cert.getIssuerDN() +"\n"+ cert.getSubjectDN();
				int ret = JOptionPane.showConfirmDialog(null, infoString);
				
				if (ret != JOptionPane.YES_OPTION) {
					System.out.println("Cancelou");
					DBManager.insereRegistro(6007, (String) user.get("email"));
					return;
				}
				else {
					DBManager.insereRegistro(6006, (String) user.get("email"));
				}
			
				
				if (senha.equals(confirmacao)) {
					if (Auth.cadastraUsuario(grupo, senha, certificadoDigitalLabel.getText(), tanListLabel.getText())) {
						JOptionPane.showMessageDialog(null, "Usuário cadastrado!");
						dispose();
						new CadastroView(user);
					}
					else {
						DBManager.insereRegistro(6003, (String) user.get("email"));
						JOptionPane.showMessageDialog(null, "Não foi possível cadastrar novo usuário.");
					}
				}
				else {
					DBManager.insereRegistro(6003, (String) user.get("email"));
					JOptionPane.showMessageDialog(null, "Senha e confirmação de senha não são iguais.");
				}
				
			}
		});
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.setBounds(150, 570, 150, 40);
		c.add(voltarButton);
		voltarButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				DBManager.insereRegistro(6008, (String) user.get("email"));
				dispose();
				new MainView(user);
			}
		});
		
	}	
}
