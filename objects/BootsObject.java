package objects;

import javax.imageio.ImageIO;

public class BootsObject extends SuperObject{
	public BootsObject() {
		try {
			name = "boots";
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
