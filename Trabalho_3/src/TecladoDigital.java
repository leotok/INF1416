import javax.swing.*;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class TecladoDigital {

	private JFrame frame;
	private List<List<String>> opcoes;
	private int keysPressed;
	private List<List<String>> listaOpcoesEscolhidas;

	private TecladoDigitalListener listener;

	public TecladoDigital (TecladoDigitalListener listener){
		//Inicializa e embaralha a lista de fonemas
		opcoes = geraOpcoes();
		this.listener = listener;
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
			System.out.println(numeros);
		}
		return list;
	}

	public void show() {	
		keysPressed = 0;

		frame = new JFrame("Digital Keyboard");
		frame.setLayout(new FlowLayout());

		drawKeys();
		
		frame.setResizable(false);
		frame.setSize(300, 150);
		frame.setVisible(true);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	public void dismiss() {
		frame.setVisible(false);
		frame.dispose();
	}

	private void drawKeys() {
		for (int i = 0; i < opcoes.size(); i++){
			DigitalKey tecla = new DigitalKey(opcoes.get(i));
			tecla.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) { 
					
					listaOpcoesEscolhidas.add(tecla.opcao);
					
					System.out.println(keysPressed);
					System.out.println(tecla.opcao);

					keysPressed++;

					if(keysPressed == 8) 
						listener.onCombinationsPrepared(generateCombinations());
				} 
			});
			frame.add(tecla);
		}
	}

	private List<String> generateCombinations() {
		ArrayList<String> combs = new ArrayList<String>();
		for (int i=0; i<listaOpcoesEscolhidas.size(); i++) {
			
		}
		
		for (String f1 : listaOpcoesEscolhidas.get(0)) {
			for (String f2 : listaOpcoesEscolhidas.get(1)) {
				for (String f3 : listaOpcoesEscolhidas.get(2)) {
					for (String f4 : listaOpcoesEscolhidas.get(2)) {
						for (String f5 : listaOpcoesEscolhidas.get(2)) {
							for (String f6 : listaOpcoesEscolhidas.get(2)) {
								for (String f7 : listaOpcoesEscolhidas.get(2)) {
									for (String f8 : listaOpcoesEscolhidas.get(2)) {
										combs.add(f1+f2+f3+f4+f5+f6+f7+f8);
									}
								}	
							}	
						}	
					}
				}
			}
		}

		return combs;
	}

	
	public class DigitalKey extends JButton {

		private static final long serialVersionUID = 8645381884191901147L;
		public List<String> opcao;

		public DigitalKey(List<String> opcao){
			super(String.join("\n", opcao));

			this.opcao = opcao;
		}
	}

}