package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16*16 tile
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 26;
	public final int maxScreenRow = 16;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	
	//WORLD SETTINGS
	public final int maxWorldCol = 100;
	public final int maxWorldRow = 100;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// FPS
	int FPS = 60;
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	public Player player = new Player(this, keyH);
	TileManager tileM = new TileManager(this);
	
	
	int playerX = 100, playerY = 100, playerSpeed = 4;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1_000_000_000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			//timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				// 1. Update character position information.
				update();
				// 2. Draw the screen with updated information.
				repaint();
				delta--;
				//drawCount++;
			}
			
			/* Show FPS
			if(timer >= 1000000000) {
				System.out.println("FPS : " + drawCount );
				drawCount = 0;
				timer = 0;
			}
			*/

		}
	}

	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
}
