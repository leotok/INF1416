import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LoginView extends JFrame {

	private final int width = 400;
	private final int height = 500;
	
	private HashMap user = null;
	
	public LoginView() {
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
		JLabel loginLabel = new JLabel("Login:");
		JTextField loginField = new JTextField();
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				HashMap user = Auth.autenticaEmail(loginField.getText());
				if (user != null) {
					System.out.println("Email ok");
					dispose();
					new SenhaView(user);
					
				}
				else {
					System.out.println("Email nao ok");
				}
			}
		});
		
		loginLabel.setBounds(30, 50, 300, 30);
		loginField.setBounds(30, 80, 300, 40);
		loginButton.setBounds(30, 150, 300, 40);
		
		
		c.add(loginLabel);
		c.add(loginField);
		c.add(loginButton);
	}	
}
