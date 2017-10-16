import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainView extends JFrame {

	private final int width = 800;
	private final int height = 600;
	
	public MainView() {
		setLayout(null);
		setSize (this.width, this.height);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setVisible (true);
		
		setTitle("Trabalho 3");
		
		Container c = getContentPane();
		c.add(new Header("leo@leo.com", "grupo do leo", "Leonardo"));
		c.add(new FirstBody(10));
		c.add(new SecondBody());
	}	
}
