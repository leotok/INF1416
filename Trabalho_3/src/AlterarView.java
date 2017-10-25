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


public class AlterarView extends JFrame {

	private final int width = 450;
	private final int height = 630;
	
	private HashMap user = null;
	
	public AlterarView (HashMap user) {
		this.user = user;
		
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
		List<HashMap> tanList = DBManager.retornaTanList((String)user.get("email"));
		c.add(new FirstBody("Total de OTPS", tanList.size()));
		
		
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
		
		JLabel senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(30, 290, 300, 40);
		c.add(senhaLabel);
		JPasswordField senhaField = new JPasswordField(); 
		senhaField.setBounds(30, 330, 300, 40);
		c.add(senhaField);
		
		JLabel senhaConfirmacaoLabel = new JLabel("Confirme a senha:");
		senhaConfirmacaoLabel.setBounds(30, 370, 300, 40);
		c.add(senhaConfirmacaoLabel);
		JPasswordField senhaConfirmacaoField = new JPasswordField(); 
		senhaConfirmacaoField.setBounds(30, 410, 300, 40);
		c.add(senhaConfirmacaoField);
		
		JButton alterarButton = new JButton("Alterar e voltar");
		alterarButton.setBounds(30, 450, 300, 40);
		c.add(alterarButton);
		alterarButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				String errorMsg = "";
				String senha = new String( senhaField.getPassword());
				if (senha.isEmpty() == false) {
					String confirmacao = new String(senhaConfirmacaoField.getPassword());
					if (senha.equals(confirmacao)) {
						if (Auth.verificaRegrasSenha(senha) == false) {
							errorMsg += "Senha não está de acordo com a regra.\n";
						} 
						else {
							senha = Auth.geraSenhaProcessada(senha, (String) user.get("salt"));
							DBManager.alterarSenha(senha, (String) user.get("email")) ;
						}
					}
					else {
						errorMsg += "Senha e confirmação de senha não são iguais.\n";
					}
				}
				
				String pathCertificado = certificadoDigitalLabel.getText();
				if (pathCertificado.isEmpty() == false) {
					Path cdPath = Paths.get(pathCertificado);
					byte[] certDigBytes = null;
					try {
						certDigBytes = Files.readAllBytes(cdPath);
					} catch (Exception a) {
						a.printStackTrace();
						return;
					}
					
					X509Certificate cert = Auth.leCertificadoDigital(certDigBytes);
					String certString = Auth.certToString(cert);
					DBManager.alterarCertificadoDigital(certString, (String) user.get("email"));
				}
				
				String pathTanList = tanListLabel.getText();
				if (pathTanList.isEmpty() == false) {
					DBManager.descartaTanList((String) user.get("email"));
					Auth.geraTanList(pathTanList, 10,  (String) user.get("email"));
				}
				
				if (errorMsg.isEmpty() == false) {
					JOptionPane.showMessageDialog(null, errorMsg);
				}
				dispose();
				new MainView(Auth.autenticaEmail((String) user.get("email")));
			}
		});
		
	}	
}
