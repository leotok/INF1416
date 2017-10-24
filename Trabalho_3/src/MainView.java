import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;


public class MainView extends JFrame {

	private final int width = 400;
	private final int height = 500;
	
	private HashMap user = null;
	
	public MainView(HashMap user) {
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
		
		setTitle("Menu Principal");
		
		Container c = getContentPane();
		c.add(new Header((String)user.get("email"), (String)user.get("groupName"), (String)user.get("name")));
		c.add(new FirstBody("Total de acessos", (int)user.get("totalAcessos")));
		c.add(new MainButtons());
	}	
}
