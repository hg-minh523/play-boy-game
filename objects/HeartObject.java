package objects;

import javax.imageio.ImageIO;

public class HeartObject extends SuperObject {
	public HeartObject() {
		try {
			name = "heart";
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			image1 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
