import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LoginView extends JFrame implements TecladoDigitalListener{

	private final int width = 800;
	private final int height = 600;
	
	private JPanel loginPanel;
	private TecladoDigital tecladoDigital;
	private JPanel tanListPanel;
	
	private HashMap user = null;
	
	public LoginView() {
		setSize (this.width, this.height);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new FlowLayout());
		setVisible(true);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		
		setTitle("Login");
		
		initView();
	}	
	
	private void initView() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

		p.add(new JLabel("Login:"));

		JTextField loginField = new JTextField();
		p.add(loginField);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = loginField.getText();
				user = Auth.autenticaEmail(email);
				
				if(user != null) {
					if((int) user.get("acesso") == 0) {
//						m_manager.addRegistry(2004, m_currentUser.getLogin());
						JOptionPane.showMessageDialog(null, "Usuario com acesso bloqueado temporariamente.");
						return;
					}
//					m_manager.addRegistry(2003, m_currentUser.getLogin());
					destroiLoginPanel();
//					m_manager.addRegistry(3001, m_currentUser.getLogin());
					initTecladoDigital();
					setVisible(false);
				} else {
//					m_manager.addRegistry(2005, login);
					JOptionPane.showMessageDialog(null, "Login invalido.");
				}
			}
		});
		p.add(loginButton);
		getRootPane().setDefaultButton(loginButton);

		p.setVisible(true);
		add(p);
		loginPanel = p;
	}
	
	private void initTecladoDigital() {
		tecladoDigital = new TecladoDigital(LoginView.this);
		tecladoDigital.show();
	}
	
	private void destroiTecladoDigital() {
		tecladoDigital.dismiss();
		tecladoDigital = null;
	}
	
	private void destroiLoginPanel() {
//		m_manager.addRegistry(2002);
		loginPanel.setVisible(false);
		remove(loginPanel);
		loginPanel.removeAll();
		loginPanel = null;
	}
	
	public void onCombinationsPrepared(List<String> combinations) {
		boolean passOk = false;
		for (String s : combinations) {
			if(Auth.autenticaSenha(s, user)) {
				passOk = true;
				break;
			}
		}
		destroiTecladoDigital();

		if(passOk) {
//			m_manager.addRegistry(3003, m_currentUser.getLogin());
//			m_manager.addRegistry(3002, m_currentUser.getLogin());
			createTanListPanel();
			setVisible(true);
			System.out.println("Senha correta!");
			DBManager.zeraAcessoErrado((String)user.get("email"));
		} else {
//			m_manager.addRegistry(3004, m_currentUser.getLogin());
			DBManager.incrementaAcessoErrado((String)user.get("email"));
			
//			if(m_currentUser.getPasswordErrors() == 1) 
//				m_manager.addRegistry(3005, m_currentUser.getLogin());
//			else if (m_currentUser.getPasswordErrors() == 2)
//				m_manager.addRegistry(3006, m_currentUser.getLogin());
//			else if (m_currentUser.getPasswordErrors() == 0)
//				m_manager.addRegistry(3007, m_currentUser.getLogin());

			if((int) user.get("acesso") == 0) {
//				m_manager.addRegistry(3008, m_currentUser.getLogin());
//				m_manager.addRegistry(3002, m_currentUser.getLogin());
				user = null;
				JOptionPane.showMessageDialog(null, "User blocked!");
				initView();
				setVisible(true);
			} else {
				int remainingTries = 3 - (int) user.get("numAcessoErrados");
				JOptionPane.showMessageDialog(null, "Senha incorreta, tentativas sobrando: " + remainingTries);
				initTecladoDigital();
			}
		}
	}
	
	private void createTanListPanel() {
//		m_manager.addRegistry(4001, m_currentUser.getLogin());
//		User.TanValue tanValue = m_currentUser.getTanValue();

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

//		p.add(new JLabel("One time password #" + tanValue.index));

		JTextField passwordField = new JTextField();
		p.add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String password = passwordField.getText();
//				if(password.equals(tanValue.password)) {
//					m_manager.addRegistry(4003, m_currentUser.getLogin());
//					destroyTanListPanel();
//					setVisible(false);
//
//					m_currentUser.useTanValue(tanValue);
//					m_currentUser.resetPasswordErrors();
//					RestrictedArea restrict = new RestrictedArea(m_currentUser, Window.this);
//					restrict.show();
//				} else {
//					m_currentUser.addPasswordError();
//					
//					if(m_currentUser.getPasswordErrors() == 1) 
//						m_manager.addRegistry(4004, m_currentUser.getLogin());
//					else if (m_currentUser.getPasswordErrors() == 2)
//						m_manager.addRegistry(4005, m_currentUser.getLogin());
//					else if (m_currentUser.getPasswordErrors() == 0)
//						m_manager.addRegistry(4006, m_currentUser.getLogin());
//					
//					if(m_currentUser.isBlocked()) {
//						m_manager.addRegistry(4009, m_currentUser.getLogin());
//						JOptionPane.showMessageDialog(null, "Usuario bloqueado!");
//						destroyTanListPanel();
//						m_currentUser = null;
//						createLoginPanel();
//						setVisible(true);
//					} else {
//						int remainingTries = User.MAX_ERRORS - m_currentUser.getPasswordErrors();
//						JOptionPane.showMessageDialog(null, "Senha incorreta, tentativas sobrando: " + remainingTries);
//						passwordField.setText("");
//					}
//				}
			}
		});
		p.add(loginButton);
		getRootPane().setDefaultButton(loginButton);
		p.setVisible(true);
		
		add(p);
		tanListPanel = p;
	}

	private void destroiTanListPanel() {
//		m_manager.addRegistry(4002, m_currentUser.getLogin());
		tanListPanel.setVisible(false);
		remove(tanListPanel);
		tanListPanel.removeAll();
		tanListPanel = null;
	}
	
	public void onRestrictedAreaExit() {
		
		initView();
		setVisible(true);
	}
}
