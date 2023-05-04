package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	//FPS
	int FPS = 60;
	
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;//il thread è uno switch che possiamo accendere e spegnere, il programma gira finché non va off; 
	//dalla libreria java Runnable
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);// questo aumenta le performance in rendering
		this.addKeyListener(keyH);
		this.setFocusable(true);//con questo il gamepanel prende il "focus" per ricevere il key input
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		
		while(gameThread != null) {
	
			//UPDATE: aggiorna info come posizione personaggi
			update();
			//DRAW: renderizza le immagini aggiornate
			repaint();
			
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		else if(keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		else if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed == true){
			playerX += playerSpeed;
		}
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
	}
	
}









