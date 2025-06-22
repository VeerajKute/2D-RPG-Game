package entity;

import java.awt.image.BufferedImage;

public class Entity {
	public int worldX;
	public int worldY;
	int speed;
	
	public BufferedImage up1, up2, up3, left1, left2, left3, left4, left5, left6, right1, right2, right3, right4, right5, right6, down1, down2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
