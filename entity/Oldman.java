package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Oldman extends Entity {
//	GamePanel gp;
	String textSpeak[];
	int spriteOldMan = 0;
	public int couterSpeak = 0;

	public Oldman(GamePanel gp) {
		super(gp);
		setImageOldman();
		textSpeak = new String[10];
		setDefaultValue();
		setupTextSpead();
	}

	public void setDefaultValue() {
		solidArea = new Rectangle(0, 0, 48, 48);
		solidAreaDefaulX = solidArea.x;
		solidAreaDefaulY = solidArea.y;
		speed = 1;
		derection = "up";
	}

	public void setupTextSpead() {
		textSpeak[0] = "Hello Boy";
		textSpeak[1] = "Hello Boy 1";
		textSpeak[2] = "Hello Boy 2";
		textSpeak[3] = "Hello Boy 3";
		textSpeak[4] = "Hello Boy 4";

	}

	public void setImageOldman() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setAction() {
		int random = (int) Math.floor(Math.random() * 100 + 1);
		spriteOldMan++;
		if (spriteOldMan > 120) {
			if (random > 0 && random <= 25) {
				derection = "up";
			}
			if (random > 26 && random <= 50) {
				derection = "down";
			}
			if (random > 51 && random <= 75) {
				derection = "left";
			}
			if (random > 76) {
				derection = "right";
			}
			spriteOldMan = 0;
		}
	}

	public void speak() {
		if (textSpeak[couterSpeak] == null) {
			couterSpeak = 0;
		} else {
			gp.ui.drawStringDiaLog(textSpeak[couterSpeak]);
			couterSpeak++;
		}
		
		switch (gp.player.derection) {
		case "up":
			derection = "down";
			break;
		case "down":
			derection = "up";
			break;
		case "right":
			derection = "left";
			break;
		case "left":
			derection = "righ";
			break;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch (derection) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			} else if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			} else if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			} else if (spriteNum == 2) {
				image = right2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			} else if (spriteNum == 2) {
				image = left2;
			}
			break;
		default:
			if (spriteNum == 1) {
				image = right1;
			} else if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
//	     int worldX = gp.player.worldX + gp.player.screenX;
//	     int worldY= gp.player.worldY + gp.player.screenY;
		g2.drawImage(image, worldX, worldY, gp.titleSize, gp.titleSize, null);

	}
}
