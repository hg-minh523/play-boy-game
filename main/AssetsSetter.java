package main;

import entity.Oldman;
import objects.BootsObject;
import objects.ChestObject;
import objects.DoorObject;
import objects.KeyObject;
import objects.SwordObject;

public class AssetsSetter {
	GamePanel gp;
	public AssetsSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setterObject() {
		gp.obj_Arr[0] = new KeyObject();
		gp.obj_Arr[0].worldX = 23 * gp.titleSize;
		gp.obj_Arr[0].worldY = 12 * gp.titleSize;
//
		gp.obj_Arr[1] = new ChestObject();
		gp.obj_Arr[1].worldX = 12 * gp.titleSize;
		gp.obj_Arr[1].worldY = 8 * gp.titleSize;
		
		
		gp.obj_Arr[2] = new DoorObject();
		gp.obj_Arr[2].worldX = 12 * gp.titleSize;
		gp.obj_Arr[2].worldY = 12 * gp.titleSize;
		
		gp.obj_Arr[3] = new SwordObject();
		gp.obj_Arr[3].worldX = 12 * gp.titleSize;
		gp.obj_Arr[3].worldY = 13 * gp.titleSize;
		
		gp.obj_Arr[4] = new BootsObject();
		gp.obj_Arr[4].worldX = 12 * gp.titleSize;
		gp.obj_Arr[4].worldY = 14 * gp.titleSize;
	}
	
	public void setterNPC() {
		gp.entityList[0] = new Oldman(gp);
		gp.entityList[0].worldX =  24* gp.titleSize;
		gp.entityList[0].worldY =  25* gp.titleSize;
	}
}	
