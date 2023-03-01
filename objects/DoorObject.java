package objects;

import javax.imageio.ImageIO;

public class DoorObject extends SuperObject {
	public DoorObject() {
		try {
			name = "door";
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
