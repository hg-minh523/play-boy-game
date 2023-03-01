package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	public BufferedImage image,image1,image2;
	public boolean collision = false;
	public int worldX;
	public int worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public String name ;
	
	public void draw(Graphics2D g2,GamePanel gp) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if(screenX   < gp.player.worldX + gp.player.screenX &&
				screenX + gp.titleSize>= 0 &&
				screenY+ gp.titleSize >= 0 &&
				screenY <= gp.player.worldY + gp.player.screenY
					) {
				g2.drawImage(image,screenX, screenY,gp.titleSize,gp.titleSize,null);
				
			}
//		g2.drawImage(image,screenX, screenY,gp.titleSize,gp.titleSize,null);
	}
}
