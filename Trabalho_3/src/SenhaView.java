import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class SenhaView extends JFrame {
	
	private final int width = 400;
	private final int height = 500;
	
	private HashMap user = null;
	private Node root = new Node("");
	private int numCliques = 0;
	
	public SenhaView(HashMap user) {
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
		
		
		Container c = getContentPane();
		
		JLabel senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(30, 100, 200, 50);
		c.add(senhaLabel);
		
		JTextField passwordField = new JTextField();
		passwordField.setEnabled(false);
		passwordField.setBounds(30, 150, 200, 50);
		c.add(passwordField);
		
		List<List<String>> opcoes = geraOpcoes();
		
		List<JButton> listaButtons = new ArrayList<JButton>();
		
		for (int i=0; i<5; i++) {
			JButton senhaButton = new JButton(String.join(" ", opcoes.get(i)));
			senhaButton.setBounds(30 + (i * 55), 300, 50, 50);
			senhaButton.addActionListener(new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					if (numCliques == 8) {
						System.out.println("Senha tem no max 8 numeros");
						return;
					}
					numCliques++;
					passwordField.setText(passwordField.getText() + "*");
					insereNosFolhas(root, ((JButton)e.getSource()).getText());
					
					sorteiaBotoes(listaButtons);
				}
			});
			listaButtons.add(senhaButton);
			c.add(senhaButton);
		}		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(60, 400, 300, 50);
		c.add(loginButton);
		
		loginButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				int tamSenha = passwordField.getText().length();
				if (tamSenha < 6 || tamSenha > 8) {
					System.out.println("Tamanho invalido");
				}
				else {
					if (Auth.verificaArvoreSenha(root, user, "")) {
						System.out.println("Senha correta!");
						dispose();
						new TanListView(user);
					}
					else {
						System.out.println("Senha incorreta");
					}
				}
			}
		});
	}	
	
	private void insereNosFolhas(Node root, String opcoes) {
		if (root.dir == null && root.esq == null) {
			root.esq = new Node(""+opcoes.charAt(0));
			root.dir = new Node(""+opcoes.charAt(2));
			return;
		}
		insereNosFolhas(root.dir, opcoes);
		insereNosFolhas(root.esq, opcoes);
	}
	
	private void sorteiaBotoes(List<JButton> lista) {
		List<List<String>> opcoes = geraOpcoes();
		for (int i=0; i<5; i++) {
			JButton btn = lista.get(i);
			btn.setText(String.join("\n", opcoes.get(i)));
		}
	}
	
	private List<List<String>> geraOpcoes() {
		List<List<String>> list = new ArrayList<List<String>>();
		String numeros = "0123456789";
		
		for (int i=0; i < 5; i++) {
			Random rand = new Random();
			List<String> opcao = new ArrayList<String>();
			int index = rand.nextInt(numeros.length());
			opcao.add(""+numeros.charAt(index));
			numeros = numeros.replaceAll("" + numeros.charAt(index), "");
			index = rand.nextInt(numeros.length());
			opcao.add(""+numeros.charAt(index));
			numeros = numeros.replaceAll("" + numeros.charAt(index), "");
			list.add(opcao);
		}
		return list;
	}
}
