package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	public Rectangle eventRect;
	public int solidDefaultX;
	public int solidDefaultY;
	
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventRect = new Rectangle(23,23,2,2);
		solidDefaultX = eventRect.x;
		solidDefaultY = eventRect.y;
	}
	
	public void checkEvent() {
		if (hit(27,16,"right") == true) {
//			System.out.println("right");
		}
	}
	
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		boolean hit = false;
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		eventRect.x = eventCol * gp.titleSize + eventRect.x;
		eventRect.y = eventRow * gp.titleSize + eventRect.y;
		if(gp.player.solidArea.intersects(eventRect)) {
			if (gp.player.derection.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
	
		eventRect.x = solidDefaultX;
		eventRect.y = solidDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaulX;
		gp.player.solidArea.y = gp.player.solidAreaDefaulX;
		return hit;
	}
}
