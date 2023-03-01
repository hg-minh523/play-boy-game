package objects;

import javax.imageio.ImageIO;

public class KeyObject extends SuperObject{
	public KeyObject() {
		try {
			name = "key";
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
