package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	//SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile, dimensione base dei pg e delle tessere della mappa
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; // le tile diventano 48x48
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 px
	final int screenHeight = tileSize * maxScreenRow; // 576 px
	//otteniamo così un ratio 4:3
	
	Thread gameThread;//il thread è uno switch che possiamo accendere e spegnere, il programma gira finché non va off; 
	//dalla libreria java Runnable
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);// questo aumenta le performance in rendering
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {
		
		while(gameThread != null) {
			
			//System.out.println("The game loop is running");
			
			//UPDATE: aggiorna info come posizione personaggi
			update();
			//DRAW: renderizza le immagini aggiornate
			repaint();
		}
	}
	
	public void update() {
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
	}
	
}









