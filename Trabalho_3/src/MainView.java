

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class MainView extends JFrame {

	private final int width = 800;
	private final int height = 600;
	
	public MainView() {
		setSize (this.width, this.height);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new FlowLayout());
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		
		setTitle("Trabalho 3");
		
		Container c = getContentPane();
		c.add(new Header("leo@leo.com", "grupo do leo", "Leonardo"));
		c.add(new FirstBody("Total de acessos", 10));
		c.add(new MainButtons());
	}	
}
