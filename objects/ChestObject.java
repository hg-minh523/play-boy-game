package objects;

import javax.imageio.ImageIO;

public class ChestObject extends SuperObject {
	public ChestObject() {
		try {
			name = "chest";
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
