package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManagerment {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManagerment(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldY][gp.maxWorldX];
		getTileImage();
		loadMap();
	}

	public void getTileImage() {
		setup("000", false, 0);
		setup("001", false, 1);
		setup("002", false, 2);
		setup("003", false, 3);
		setup("004", false, 4);
		setup("005", false, 5);
		setup("006", false, 6);
		setup("007", false, 7);
		setup("008", false, 8);
		setup("009", false, 9);
		setup("010", false, 10);
		setup("011", false, 11);
		setup("012", false, 12);
		setup("013", false, 13);
		setup("014", false, 14);
		setup("015", false, 15);
		setup("016", true, 16);
		setup("017", false, 17);
		setup("018", true, 18);
		setup("019", true, 19);
		setup("020", true, 20);
		setup("021", true, 21);
		setup("022", true, 22);
		setup("023", true, 23);
		setup("024", true, 24);
		setup("025", true, 25);
		setup("026", true, 26);
		setup("027", true, 27);
		setup("028", true, 28);
		setup("029", true, 29);
		setup("030", true, 30);
		setup("031", true, 31);
		setup("032", true, 32);
		setup("033", false, 33);
		setup("034", false, 34);
		setup("035", false, 35);
		setup("036", false, 36);
		setup("037", false, 37);
//
//		setup("000",false,38);
//		setup("earth",false,39);
//		setup("wall",false,40);
//
//		setup("true",false,41);

	}

	public void setup(String pathName, boolean colission, int index) {
		try {

			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + pathName + ".png"));
			tile[index].colission = colission;

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void loadMap() {
		InputStream in = getClass().getResourceAsStream("/map/worldmap.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		try {
			int col = 0;
			int row = 0;
			while (col < gp.maxWorldX && row < gp.maxWorldY) {
				String line = br.readLine();
				while (col < gp.maxWorldX) {
					String num[] = line.split(" ");
					int numConvert = Integer.parseInt(num[col]);
					mapTileNum[col][row] = numConvert;
					col++;
				}
				if (col == gp.maxWorldX) {
					col = 0;
					row++;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g2) {

		int row = 0;
		int col = 0;
		while (gp.maxWorldX > col && gp.maxWorldX > row) {
			int number = mapTileNum[col][row];

			int worldX = col * gp.titleSize;
			int worldY = row * gp.titleSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
//			System.out.println( gp.player.worldX - gp.player.screenX);
			if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.titleSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.titleSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[number].image, screenX, screenY, gp.titleSize, gp.titleSize, null);

			}
			col++;
			if (col == gp.maxWorldX) {
				col = 0;
				row++;
			}

		}

	}

}
