package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import objects.HeartObject;
import objects.KeyObject;

public class UI {
	GamePanel gp;
	public Font font;
	public boolean showMessage = false;
	public String messageText = "";
	int messageCouter = 0;
	boolean showMessageCongratulationOn = false;
	public boolean showDialogOn = false;
	String dialogText = "";
	public UI(GamePanel gp) {
		this.gp = gp;
		font = new Font("Arial", Font.PLAIN, 20);
	}

	public void draw(Graphics2D g2) {
//		DRAW KEY;
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		KeyObject key = new KeyObject();
		g2.drawImage(key.image, 0, 0, 50, 50, null);

		int messageScreenX = gp.titleSize;
		int messageScreenY = gp.player.screenY;
		g2.drawString("X " + gp.player.numKey, 50, 25);
//		DRAW HERAT BAR
		HeartObject poolHear = new HeartObject();
		int x = gp.screenMaxCol - 20;
		for ( int i = 0 ; i <gp.player.poolHeart;i+=2) {
			x-=gp.titleSize;
			g2.drawImage(poolHear.image, x, 10, 50, 50, null);
		}
// DRAW PICK UP OBJECT
		if (gp.gameState == gp.dialogState) {
			drawWindow(g2,dialogText);
		}

		if (showMessage == true) {
			g2.drawString(this.messageText, messageScreenX, messageScreenY);
			messageCouter++;
		}
		if (showMessageCongratulationOn == true) {
			int screenX = gp.screenMaxCol / 4;
			int screenY = gp.screenMaxRow / 4;
			g2.drawString("Congratulation", screenX, screenY);
			messageCouter++;
		}
		if (messageCouter > 120) {
			showMessage = false;
			showDialogOn = false;
			messageCouter = 0;
		}
		if (gp.gameState == gp.pauseState) {
			String text = "PAUSE";
			g2.setFont(new Font("Arial", Font.BOLD, 40));
			int length = gp.screenMaxCol/2 - centerScreenX(g2,text)/2;
			int height = gp.screenMaxRow/2 ;
			g2.drawString(text, length, height);
		}
	}

	public int centerScreenX(Graphics2D g2,String text) {

		int length = (int)g2.getFontMetrics().stringWidth(text);
		return length;
	}
	
	public void showMessageCongratulation() {
		showMessageCongratulationOn = true;
	}

	public void showMessage(String nameObject) {
		if (nameObject == "key") {
			showMessage = true;
			messageText = "Got a Key";
		}
		if (nameObject == "door") {
			showMessage = true;
			messageText = "Open the door ";
		}
		if (nameObject == "chest") {
			showMessage = true;
			messageText = "Got a chest";
		}
		if (nameObject == "boots") {
			showMessage = true;
			messageText = "Speed Up";
		}
		if (nameObject == "sword") {
			showMessage = true;
			messageText = "Attack Up";
		}
	}

	public void drawWindow(Graphics2D g2,String dialogText) {
		int x = 2 * gp.titleSize;
		int y = gp.titleSize;
		int width = 12 * gp.titleSize;
		int height = 4 * gp.titleSize;
		Color c  = new Color(255, 255, 255);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(0, 0, 0,200);
		g2.setStroke(new BasicStroke());
		g2.setColor(c);
		g2.fillRoundRect(x + 4, y + 4, width - 10, height - 10, 25, 25);
//		dialogText = drawStringDiaLog(s);
		g2.setColor(new Color(255, 255, 255));
		g2.drawString(dialogText, x+gp.titleSize, y+gp.titleSize);
	}
	
	public void drawStringDiaLog(String s) {
//		return s;
		dialogText = s;
	}
	public void drawTitleScreen(Graphics2D g2) {
//		DRAW TITLE
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0,0,gp.screenMaxCol,gp.screenMaxRow);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
		String text = "Blue Boy Adventure";
		int x = gp.screenMaxCol/2 - centerScreenX(g2, text)/2;
		int y = gp.titleSize * 3;
//		SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
//		Main
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		x= gp.screenMaxCol/2 - gp.titleSize ;
		y+=gp.titleSize;
//		DRAW PLAYER
		g2.drawImage(gp.player.down1, x, y, gp.titleSize*2, gp.titleSize*2,null);
//		DRAW MENU
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		text = "New Game";
		y+=gp.titleSize*4;
		x= gp.screenMaxCol/2 - centerScreenX(g2, text)/2;
		g2.drawString(text, x, y);
		if(gp.keyHandle.numMenu == 0) {
			g2.drawString(">", x - gp.titleSize, y);
		}
		
		text = "Option";
		y+=gp.titleSize;
		x= gp.screenMaxCol/2 - centerScreenX(g2, text)/2;
		g2.drawString(text, x, y);
		if(gp.keyHandle.numMenu == 1) {
			g2.drawString(">", x - gp.titleSize, y);
		}
		
		text = "Quit";
		y+=gp.titleSize;
		x= gp.screenMaxCol/2 - centerScreenX(g2, text)/2;
		g2.drawString(text, x, y);
		if(gp.keyHandle.numMenu == 2) {
			g2.drawString(">", x - gp.titleSize, y);
		}
	}
	
	public void drawTitleScreenV2(Graphics2D g2) {
//		DRAW TITLE
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0,0,gp.screenMaxCol,gp.screenMaxRow);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
		String text = "Choose your class";
		int x = gp.screenMaxCol/2 - centerScreenX(g2, text)/2;
		int y = gp.titleSize * 3;
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
//		DRAW PLAYER
		x= gp.screenMaxCol/2 - gp.titleSize ;
		y+=gp.titleSize;
		g2.drawImage(gp.player.down1, x, y, gp.titleSize*2, gp.titleSize*2,null);
//		DRAW MENU
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		text = "Knight";
		y+=gp.titleSize*4;
		x= gp.screenMaxCol/2 - centerScreenX(g2, text)/2;
		g2.drawString(text, x, y);
		if(gp.keyHandle.numMenu == 0) {
			g2.drawString(">", x - gp.titleSize, y);
		}
		
		text = "Wizard";
		y+=gp.titleSize;
		x= gp.screenMaxCol/2 - centerScreenX(g2, text)/2;
		g2.drawString(text, x, y);
		if(gp.keyHandle.numMenu == 1) {
			g2.drawString(">", x - gp.titleSize, y);
		}
		
		text = "Archer";
		y+=gp.titleSize;
		x= gp.screenMaxCol/2 - centerScreenX(g2, text)/2;
		g2.drawString(text, x, y);
		if(gp.keyHandle.numMenu == 2) {
			g2.drawString(">", x - gp.titleSize, y);
		}
	}
}
