package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuInicial{
	
	private Image fundo;
	
	public MenuInicial() {
		ImageIcon referencia = new ImageIcon("res\\menu.png");
		fundo = referencia.getImage();
	}
	
	public void show() {

        JFrame frame = new JFrame("Menu Bravia Escape");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); 
        frame.setResizable(false);  
        frame.setLocationRelativeTo(null);
        
        MeuPainelMenu painel = new MeuPainelMenu();
        painel.setLayout(null);
        frame.add(painel);
        
        JButton botao1 = new JButton("Fase 1");
        botao1.setBounds(200, 180, 100,70);
        botao1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Tela1 tela1 = new Tela1();
				tela1.show();
				
			}
		});
        
        
        JButton botao2 = new JButton("Fase 2");
        botao2.setBounds(200 + 110, 180, 100,70);
        botao2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Tela2 tela2 = new Tela2();
				tela2.show();
			}
		});
        painel.add(botao1);
        painel.add(botao2);
        
        frame.setVisible(true);

    }
	
	private class MeuPainelMenu extends JPanel{  //criado apenas para sobrecarregar paintComponent() e colocar fundo
		private static final long serialVersionUID = 3L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);    // fill background
	        setBackground(Color.lightGray); // set its background color
	        
	        Graphics2D graficos = (Graphics2D) g;
			graficos.drawImage(fundo, 0, 0, null);
			
		}
	}
	
	
}

