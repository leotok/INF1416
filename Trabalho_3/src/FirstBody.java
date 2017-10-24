import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstBody extends JPanel {

	public FirstBody(String subHeader, int totalAccess) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBounds(30, 100, 300, 30);
		
		JLabel totalAccessLabel = new JLabel(String.format("%s: %d",subHeader, totalAccess));
		add(totalAccessLabel);
	
	}
}
