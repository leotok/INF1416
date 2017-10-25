
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainButtons extends JPanel {

	public MainButtons(HashMap user) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBounds(30, 150, 350, 200);
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		JLabel mainManu = new JLabel("Menu principal:");
		
		JButton cadastroButton = new JButton("Cadastrar novo usuário");
		cadastroButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				System.out.println("Cadastrar novo usuário");
			}
		});
		JButton alterarButton = new JButton("Alterar senha pessoal, certificado digital e TAN List");
		alterarButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				System.out.println("Alterar senha pessoal, certificado digital e TAN List");
			}
		});
		
		JButton consultarButton = new JButton("Consultar pasta de arquivos secretos");
		consultarButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				System.out.println("Consultar pasta de arquivos secretos");
				topFrame.dispose();
				new ConsultarArquivosView(Auth.autenticaEmail((String)user.get("email")));
			}
		});
		
		JButton sairButton = new JButton("Sair do Sistema");
		sairButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				System.out.println("Sair do Sistema");
			}
		});
		
		add(mainManu);
		add(cadastroButton);
		add(alterarButton);
		add(consultarButton);
		add(sairButton);
	}
}
