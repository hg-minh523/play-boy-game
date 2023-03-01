package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
//	GamePanel gp;
	KeyHandler keyHandler;
	int spriteNum = 1;
	int spriteCouter = 0;
	public int screenX;
	public int screenY;
	public int numKey = 0;

	public Player(GamePanel gp, KeyHandler keyHandler) {
//		this.gp = gp ;
		super(gp);
		this.keyHandler = keyHandler;
		screenX = gp.screenMaxCol / 2 - gp.titleSize / 2;
		screenY = gp.screenMaxRow / 2 - gp.titleSize / 2;
		setDefaulsValue();
		getDefaulsImage();
	}

	public void setDefaulsValue() {
		worldX = gp.titleSize * 23;
		worldY = gp.titleSize * 25;
		solidArea = new Rectangle(8, 16, 32, 32);
		solidAreaDefaulX = solidArea.x;
		solidAreaDefaulY = solidArea.y;
		poolHeart = 6;
		speed = 4;
		derection = "down";
		
	}

	public void getDefaulsImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_2.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void update() {
		if (keyHandler.isUp || keyHandler.isDown || keyHandler.isLeft || keyHandler.isRight) {
			if (keyHandler.isUp == true) {
				derection = "up";
			} else if (keyHandler.isDown == true) {
				derection = "down";
			} else if (keyHandler.isLeft == true) {
				derection = "left";
			} else if (keyHandler.isRight == true) {
				derection = "right";
			}

			// Check Tile
			collisionOn = false;
			gp.cChecker.checkTile(this);
			// Check Object
			int checkO = gp.cChecker.checkObject(this, true);
			intersectsObject(checkO);
			// Check Entity
			int checkE = gp.cChecker.checkEntity(this, gp.entityList);
			intersectsEntity(checkE);
			if (collisionOn == false) {
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
			if (spriteCouter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCouter = 0;
			}
		}

	}

	public void intersectsObject(int index) {
		if (index != 999) {
			String nameObject = gp.obj_Arr[index].name;
			if (nameObject == "key") {
				gp.obj_Arr[index] = null;
				gp.ui.showMessage(nameObject);
				numKey++;
			}
			if (nameObject == "door") {
				if (numKey > 0) {
					gp.obj_Arr[index] = null;
					gp.ui.showMessage(nameObject);
					numKey--;
				} else {
					collisionOn = true;
				}

			}
			if (nameObject == "boots") {
				gp.obj_Arr[index] = null;
				gp.ui.showMessage(nameObject);
				speed += 1;
			}
			if (nameObject == "sword") {
				gp.obj_Arr[index] = null;
				gp.ui.showMessage(nameObject);
			}
			if (nameObject == "chest") {
				System.out.println("chest");
				gp.ui.showMessageCongratulation();
			}

		}
	}

	public void intersectsEntity(int index) {
		if (index != 999) {
				gp.entityList[index].speak();
				gp.ui.showDialogOn = true;
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
		g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
	}
}
