import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {

	public Header(String email, String group ,String name) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBounds(80, 20, 300, 60);
		
		this.setBackground(Color.BLUE);
		
		JLabel emailLabel = new JLabel(String.format("Email: %s", email));
		JLabel groupLabel = new JLabel(String.format("Grupo: %s", group));
		JLabel nameLabel = new JLabel(String.format("Nome: %s", name));
				
		add(emailLabel);
		add(groupLabel);
		add(nameLabel);
	}
}
