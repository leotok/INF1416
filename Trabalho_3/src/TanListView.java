import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class TanListView extends JFrame {

	private final int width = 400;
	private final int height = 500;
	
	private HashMap user = null;
	
	public TanListView(HashMap user) {
		this.user = user;
		DBManager.insereRegistro(4001, (String) user.get("email"));
		
		setLayout(null);
		setSize (this.width, this.height);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setTitle("Login");
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		
		
		Container c = getContentPane();
		
		
		List<HashMap> tanList = DBManager.retornaTanList((String)user.get("email"));
		Random rand = new Random();
		int index = rand.nextInt(tanList.size());
		int posicaoTan = (int) tanList.get(index).get("posicao");
		
		JLabel tanLabel = new JLabel(String.format("TAN List na posicao %d", posicaoTan));
		
		JTextField tanField = new JTextField();
		
		JButton tanButton = new JButton("Login");
		tanButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				HashMap updatedUser = Auth.autenticaEmail((String) user.get("email"));
				Integer acessosNegados = ((Integer) updatedUser.get("numTanErrada"));
				
				if (acessosNegados >= 3) {		
					DBManager.insereRegistro(4007, (String) updatedUser.get("email"));
					JOptionPane.showMessageDialog(null, "TAN incorreta. Número total de erros atingido. Aguarde até 2 minutos para tentar novamente.");
					dispose();
					new LoginView();
				}
				
				String tanInserido = tanField.getText();
				List<HashMap> tanList = DBManager.retornaTanList((String)user.get("email"));
				String tanEsperado = (String) tanList.get(index).get("tan");
				
				if (tanInserido.equals(tanEsperado)) {
					System.out.println("Acertou o TAN");
					DBManager.insereRegistro(4003, (String) updatedUser.get("email"));
					DBManager.insereRegistro(4002, (String) updatedUser.get("email"));
					DBManager.zeraAcessoErrado((String)updatedUser.get("email"));
					DBManager.marcaTanUsada((String)updatedUser.get("email"), (int)tanList.get(index).get("id"));
					DBManager.incrementaTotalAcessos((String)updatedUser.get("email"));
					dispose();
					new MainView(Auth.autenticaEmail((String)updatedUser.get("email")));
				}
				else {
					JOptionPane.showMessageDialog(null, "TAN incorreto.");
					
					DBManager.incrementaTanErrada((String)updatedUser.get("email"));
					updatedUser = Auth.autenticaEmail((String) updatedUser.get("email"));
					acessosNegados = ((Integer) updatedUser.get("numTanErrada"));
					
					if (acessosNegados == 1) {
						DBManager.insereRegistro(4004, (String) updatedUser.get("email"));
					}
					else if (acessosNegados == 2) {
						DBManager.insereRegistro(4005, (String) updatedUser.get("email"));
					}
					else if (acessosNegados == 3) {		
						DBManager.insereRegistro(4006, (String) updatedUser.get("email"));
					}
					
					
					tanField.setText("");
				}
			}
		});
		
		tanLabel.setBounds(30, 50, 300, 30);
		tanField.setBounds(30, 80, 300, 40);
		tanButton.setBounds(30, 150, 300, 40);
		
		
		c.add(tanLabel);
		c.add(tanField);
		c.add(tanButton);
	}	
}
