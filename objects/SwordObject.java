package objects;

import javax.imageio.ImageIO;

public class SwordObject extends SuperObject {
	public SwordObject() {
		try {
			name = "sword";
			image = ImageIO.read(getClass().getResourceAsStream("/objects/sword_normal.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
