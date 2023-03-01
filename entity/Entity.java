package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	public  int speed;
	GamePanel gp;
	public int worldX;
	public int worldY;
	public int screenEnX;
	public int screenEnY;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String derection;
	public Rectangle solidArea;
	public int solidAreaDefaulX ;
	public int solidAreaDefaulY ;
	public boolean collision = false;
	public boolean collisionOn = false;
	public int spriteCouter = 0;
	public int spriteNum = 1;
	public int poolHeart;
	
	public void setAction() {};
	public void speak() {};
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void update() {
		setAction();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkPlayer(this);
//		int checkE = gp.cChecker.checkEntity(this, gp.entityList);
		if(collisionOn == false) {
			 switch (derection) {
				case "up": 
					worldY -= speed;
					break;
				case "down": 
					worldY += speed;
					break;
				case "right": 
					worldX += speed;
					break;
				case "left": 
					worldX -= speed;
					break;
				}
		 }
		 spriteCouter++;
		 if (spriteCouter > 12 ) {
			 if(spriteNum == 1) {
				 spriteNum =2;
			 }else if (spriteNum == 2) {
				 spriteNum = 1;
			 }
			 spriteCouter = 0;
		 }
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
	     
	    	 BufferedImage image = null;	
		     switch (derection) {
		     	case "up":
		     		if (spriteNum == 1 ) {	     			
		     			image = up1;
		     		}else if (spriteNum == 2 ) {
		     			image = up2;
		     		}
		     		break;
		     	case "down":
		     		if (spriteNum == 1 ) {	     			
		     			image = down1;
		     		}else if (spriteNum == 2 ) {
		     			image = down2;
		     		}
		     		break;
		     	case "right":
		     		if (spriteNum == 1 ) {	     			
		     			image = right1;
		     		}else if (spriteNum == 2 ) {
		     			image = right2;
		     		}
		     		break;
		     	case "left":
		     		if (spriteNum == 1 ) {	     			
		     			image = left1;
		     		}else if (spriteNum == 2 ) {
		     			image = left2;
		     		}
		     		break;
		     	default: 
		     		if (spriteNum == 1 ) {	     			
		     			image = right1;
		     		}else if (spriteNum == 2 ) {
		     			image = right2;
		     		}
		     		break;
		     }
		     int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
//		     int worldX = gp.player.worldX + gp.player.screenX;
//		     int worldY= gp.player.worldY + gp.player.screenY;
		 	 g2.drawImage(image, screenX ,screenY ,gp.titleSize,gp.titleSize,null);

			
		
		
	     
	}
}
