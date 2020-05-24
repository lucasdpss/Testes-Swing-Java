package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tela2 extends JPanel{
	private static final long serialVersionUID = 2L; //acabar com uma warning (nao descobri pra que serve esse serial)
	
	private int x_nave;
	private int y_nave;
	
	public Tela2() {
		x_nave = 30;
		y_nave = 30;
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());  //tipo uma interrupcao para quando aperta o teclado
	}
	
	public void show() {
		JFrame frame = new JFrame("Fase 2");  //cria a janela com esse titulo da string indicada
	    frame.setSize(800, 600);  //tamanho da janela
	    frame.add(new Tela2());   //esse novo frame vai usar essa propria classe para criar a tela
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //fechar ao inves de minimizar quando apertar pra fechar
	    frame.setLocationRelativeTo(null); //centralizar tela
		frame.setResizable(false);  //nao deixar o usuario alterar o tamanho da tela
	    frame.setVisible(true);   //finalmente mostrar a janela (ja chama um monte de metodos "escondidos", tipo paintComponent())

	    
	}
	
	//pelo que eu entendi a funcao paint() eh para colocar um background fixo, eu estava usando ela antes 
	// no lugar de paintComponent() e ao mover a nave ela movia, mas a nave anterior continuava aparecendo.
	//mudei a funcao de paint() para paintComponent() e agora atualiza a posicao da nave do jeito certo
	public void paintComponent(Graphics g) {
		super.paintComponent(g);    // fill background
        setBackground(Color.lightGray); // set its background color
		
		g.setColor(Color.DARK_GRAY);
		
		for(int i=0;i < 15;i++) {
			for(int j=0;j < 15;j++) {
				g.drawRect(30 + i*30, 30 + j*30, 30, 30);
			}
		}
		
		Graphics2D g2 = (Graphics2D) g;
	    Image img1 = Toolkit.getDefaultToolkit().getImage("res//nave.gif");
	    g2.drawImage(img1, x_nave, y_nave, this);
		
	}
	
	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP){
				System.out.println("li up");
				 y_nave -= 30;
			  }else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				  System.out.println("li right");
				  x_nave += 30;
			  }else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				  System.out.println("li left");
				  x_nave -= 30;
			  }else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				  System.out.println("li down");
				  y_nave += 30;
			  }
			repaint(); //faz com que atualize a tela mais rapido
		}
	}
	
}
