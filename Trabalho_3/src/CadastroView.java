

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class CadastroView extends JFrame {

	private final int width = 800;
	private final int height = 600;
	
	public CadastroView() {
		setLayout(null);
		setSize (this.width, this.height);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setVisible (true);
		
		setTitle("Trabalho 3");
		
		Container c = getContentPane();
		c.add(new Header("leo@leo.com", "grupo do leo", "Leonardo"));
		c.add(new FirstBody("Total de usuários", 10));
		c.add(new MainButtons());
	}	
}
