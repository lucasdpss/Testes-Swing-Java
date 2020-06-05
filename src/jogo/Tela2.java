package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tela2{

	private JFrame frame;
	private MeuPainelTela2 painel;
	private Clip clip;

	private int x_nave;
	private int y_nave;

	public Tela2() {
		x_nave = 30;
		y_nave = 30;
	}

	public void show() {
		frame = new JFrame("Fase 2");  //cria a janela com esse titulo da string indicada
		painel = new MeuPainelTela2();
		frame.setSize(800, 600);  //tamanho da janela
		frame.add(painel);   //esse novo frame vai usar essa propria classe para criar a tela
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //fechar ao inves de minimizar quando apertar pra fechar
		frame.setLocationRelativeTo(null); //centralizar tela
		frame.setResizable(false);  //nao deixar o usuario alterar o tamanho da tela
		frame.setVisible(true);   //finalmente mostrar a janela (ja chama um monte de metodos "escondidos", tipo paintComponent())
		playSound("sons//Fase1.wav");
	}

	private class MeuPainelTela2 extends JPanel{
		private static final long serialVersionUID = 1L;

		public MeuPainelTela2() {
			setFocusable(true);
			setDoubleBuffered(true);
			addKeyListener(new TecladoAdapter());
		}

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
			painel.repaint(); //faz com que atualize a tela mais rapido
			if(x_nave == 450 && y_nave == 450) {
				clip.stop();
				MenuInicial menu = new MenuInicial();
				menu.show();
				frame.dispose();
			}
		}
	}

	public void playSound(String musicLocation) {
		try {
			File musicPath = new File(musicLocation);

			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}else {
				System.out.println("nao achou arquivo de audio");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no playSound");
		}
	}

}
