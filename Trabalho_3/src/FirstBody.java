import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstBody extends JPanel {

	public FirstBody(int totalAccess) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBounds(80, 100, 300, 30);
		
		this.setBackground(Color.GREEN);
		
		JLabel totalAccessLabel = new JLabel(String.format("Total de acessos: %d", totalAccess));
		add(totalAccessLabel);
	
	}
}
