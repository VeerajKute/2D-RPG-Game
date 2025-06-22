package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
	
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 49;
		worldY = gp.tileSize * 96;
		speed = 3;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Up_2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/Up_3.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Left_2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/Left_3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/Left_4.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/Left_5.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/player/Left_6.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Right_2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/Right_3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/Right_4.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/Right_5.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/player/Right_6.png"));
		}catch(IOException e) { 
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(keyH.downPressed == true || keyH.upPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
				worldY -= speed;
			} else if (keyH.downPressed == true) {
				direction = "down";
				worldY += speed;
			} else if (keyH.leftPressed == true) {
				direction = "left";
				worldX -= speed;
			} else if (keyH.rightPressed == true) {
				direction = "right";
				worldX += speed;
			}
			
			spriteCounter++;
			if(spriteCounter > 10) {
				spriteNum++;
				spriteCounter = 0;
			}
			if(spriteNum > 6) {
				spriteNum = 0;
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up" :
			if(spriteNum%2 == 0)
				image = up2;
			if(spriteNum%2 == 1)
				image = up3;
			break;
		case "down" : 
			if(spriteNum%2 == 0)
				image = down1;
			if(spriteNum%2 == 1)
				image = down2;
			break;
		case "left" :
			if(spriteNum%6 == 0)
				image = left1;
			if(spriteNum%6 == 1)
				image = left2;
			if(spriteNum%6 == 2)
				image = left3;
			if(spriteNum%6 == 3)
				image = left4;
			if(spriteNum%6 == 4)
				image = left5;
			if(spriteNum%6 == 5)
				image = left6;
			break;
		case "right" :
			if(spriteNum%6 == 0)
				image = right1;
			if(spriteNum%6 == 1)
				image = right2;
			if(spriteNum%6 == 2)
				image = right3;
			if(spriteNum%6 == 3)
				image = right4;
			if(spriteNum%6 == 4)
				image = right5;
			if(spriteNum%6 == 5)
				image = right6;
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize + 32, gp.tileSize + 32, null);
	}
}
