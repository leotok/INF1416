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
				String tanInserido = tanField.getText();
				String tanEsperado = (String) tanList.get(index).get("tan");
				
				if (tanInserido.equals(tanEsperado)) {
					System.out.println("Acertou o TAN");
					DBManager.marcaTanUsada((String)user.get("email"), (int)tanList.get(index).get("id"));
					dispose();
					new MainView(user);
				}
				else {
					System.out.println("Errou o TAN");
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
